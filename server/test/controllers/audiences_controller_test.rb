require 'test_helper'

class AudiencesControllerTest < ActionController::TestCase
  setup do
    @audience = audiences(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:audiences)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create audience" do
    assert_difference('Audience.count') do
      post :create, :audience => { :name => @audience.name }
    end

    assert_redirected_to audience_path(assigns(:audience))
  end

  test "should show audience" do
    get :show, :id => @audience
    assert_response :success
  end

  test "should get edit" do
    get :edit, :id => @audience
    assert_response :success
  end

  test "should update audience" do
    patch :update, :id => @audience, :audience => { :name => @audience.name }
    assert_redirected_to audience_path(assigns(:audience))
  end

  test "should destroy audience" do
    assert_difference('Audience.count', -1) do
      delete :destroy, :id => @audience
    end

    assert_redirected_to audiences_path
  end
end
