class AlertsController < ApplicationController

  respond_to :html

  # Read the user's alerts and delete them
  def user
    @alerts = Alert.where(:user_id => params[:id])
    @alerts.each { |alert| alert.delete }
    render :index
  end


  private

    def alert_params
      params.require(:alert).permit(:user_id, :post_id)
    end
end
