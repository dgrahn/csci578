class User::RegistrationsController < Devise::RegistrationsController

  before_filter :configure_sign_up_params, :only => [:create]
  before_filter :configure_account_update_params, :only => [:update]
  protect_from_forgery :with => :null_session, :if => Proc.new {|c| !c.request.format.json? }
  respond_to :html, :json

  # GET /resource/sign_up
  # def new
  #   super
  # end

  # POST /resource
  def create
    unless params[:format] == "json"
      super
      return
    end
    
    # Delete the user if it matches our test email
    if params[:email] == "test@grahn.us"
      puts "Deleting Test User"
      User.find_by_email(params[:email]).destroy
    end
    
    # Create the user
    user = User.new
    user.given_name = params[:given_name]
    user.surname    = params[:surname]
    user.email      = params[:email]
    user.password   = params[:password]
    user.password_confirmation = params[:password]
    
    # Try to save
    if user.save
      result = Result::SIGNUP_SUCCESS
    else
      result = Result::SIGNUP_FAIL
      result[:message] = user.errors.full_messages.join(', ')
    end
    
    render :json => result.to_json

  rescue Exception => e

    puts "Error: #{e}"
    result = Result::SIGNUP_FAIL
    result[:message] = "Server error occurred."
    render :json => result.to_json
  end

  # GET /resource/edit
  # def edit
  #   super
  # end

  # PUT /resource
  #def update
  #  super
  #end

  # DELETE /resource
  # def destroy
  #   super
  # end

  # GET /resource/cancel
  # Forces the session data which is usually expired after sign
  # in to be expired now. This is useful if the user wants to
  # cancel oauth signing in/up in the middle of the process,
  # removing all OAuth session data.
  # def cancel
  #   super
  # end

  # protected

  # You can put the params you want to permit in the empty array.
  def configure_sign_up_params
    devise_parameter_sanitizer.for(:sign_up) << [:given_name, :surname]
  end

  # You can put the params you want to permit in the empty array.
  def configure_account_update_params
    devise_parameter_sanitizer.for(:account_update) << [:given_name, :surname]
  end

  # The path used after sign up.
  # def after_sign_up_path_for(resource)
  #   super(resource)
  # end

  # The path used after sign up for inactive accounts.
  # def after_inactive_sign_up_path_for(resource)
  #   super(resource)
  # end
end
