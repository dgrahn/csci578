class AudiencesController < ApplicationController
  before_action :set_audience, only: [:show, :edit, :update, :destroy]

  respond_to :html

  def index
    @audiences = Audience.all
    respond_with(@audiences)
  end

  def show
    respond_with(@audience)
  end

  def new
    @audience = Audience.new
    respond_with(@audience)
  end

  def edit
  end

  def create
    @audience = Audience.new(audience_params)
    @audience.save
    respond_with(@audience)
  end

  def update
    @audience.update(audience_params)
    respond_with(@audience)
  end

  def destroy
    @audience.destroy
    respond_with(@audience)
  end

  private
    def set_audience
      @audience = Audience.find(params[:id])
    end

    def audience_params
      params.require(:audience).permit(:name)
    end
end
