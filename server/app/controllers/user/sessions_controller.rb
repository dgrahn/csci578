class User::SessionsController < Devise::SessionsController
  # before_filter :configure_sign_in_params, only: [:create]

  protect_from_forgery :with => :null_session, :if => Proc.new {|c| !c.request.format.json? }
  respond_to :html, :json

  # GET /resource/sign_in
  # def new
  #   super
  # end

  # POST /resource/sign_in
  def create
    unless params[:format] == "json"
      super
      return
    end
    
    user = User.find_by_email(params[:username])
    
    puts "Email = #{params[:username]}"
    puts "Password = #{params[:password]}"
    
    if user.nil? || !user.valid_password?(params[:password])
      result = Result::LOGIN_FAIL
    else
      result = Result::LOGIN_SUCCESS
      result[:message] = user.authentication_token
    end
    
    puts "JSON = #{result.to_json}"

    render :json => result.to_json
  end

  # DELETE /resource/sign_out
  # def destroy
  #   super
  # end

  # protected

  # You can put the params you want to permit in the empty array.
  # def configure_sign_in_params
  #   devise_parameter_sanitizer.for(:sign_in) << :attribute
  # end
end
