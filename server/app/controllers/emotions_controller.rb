class EmotionsController < ApplicationController

  before_action :set_emotion, :only => [:show, :edit, :update, :destroy]

  respond_to :html

  def index
    @emotions = Emotion.all
    respond_with(@emotions)
  end

  def show
    respond_with(@emotion)
  end

  def new
    @emotion = Emotion.new
    respond_with(@emotion)
  end

  def edit
  end

  def create
    @emotion = Emotion.new(emotion_params)
    @emotion.save
    respond_with(@emotion)
  end

  def update
    @emotion.update(emotion_params)
    respond_with(@emotion)
  end

  def destroy
    @emotion.destroy
    respond_with(@emotion)
  end

  private
    def set_emotion
      @emotion = Emotion.find(params[:id])
    end

    def emotion_params
      params.require(:emotion).permit(:emotion, :user_id, :post_id)
    end
end
