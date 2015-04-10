class PalsController < ApplicationController
  before_action :set_pal, only: [:show, :edit, :update, :destroy]

  respond_to :html

  def index
    @pals = Pal.all
    respond_with(@pals)
  end

  def show
    respond_with(@pal)
  end

  def new
    @pal = Pal.new
    respond_with(@pal)
  end

  def edit
  end

  def create
    @pal = Pal.new(pal_params)
    @pal.save
    respond_with(@pal)
  end

  def update
    @pal.update(pal_params)
    respond_with(@pal)
  end

  def destroy
    @pal.destroy
    respond_with(@pal)
  end

  private
    def set_pal
      @pal = Pal.find(params[:id])
    end

    def pal_params
      params.require(:pal).permit(:source_user_id, :target_user_id)
    end
end
