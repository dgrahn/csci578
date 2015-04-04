require 'test_helper'

class EmotionsControllerTest < ActionController::TestCase
  setup do
    @emotion = emotions(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:emotions)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create emotion" do
    assert_difference('Emotion.count') do
      post :create, emotion: { emotion: @emotion.emotion, post_id: @emotion.post_id, user_id: @emotion.user_id }
    end

    assert_redirected_to emotion_path(assigns(:emotion))
  end

  test "should show emotion" do
    get :show, id: @emotion
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @emotion
    assert_response :success
  end

  test "should update emotion" do
    patch :update, id: @emotion, emotion: { emotion: @emotion.emotion, post_id: @emotion.post_id, user_id: @emotion.user_id }
    assert_redirected_to emotion_path(assigns(:emotion))
  end

  test "should destroy emotion" do
    assert_difference('Emotion.count', -1) do
      delete :destroy, id: @emotion
    end

    assert_redirected_to emotions_path
  end
end
