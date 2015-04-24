
class UsersController < ActionController::Base
  
  before_action :set_user, :only => [:show]

  respond_to :json

  def show
    respond_with(@user)
  end

  private
    def set_user
      @user = User.find(params[:id])
    end
end