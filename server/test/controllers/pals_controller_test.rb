require 'test_helper'

class PalsControllerTest < ActionController::TestCase
  setup do
    @pal = pals(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:pals)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create pal" do
    assert_difference('Pal.count') do
      post :create, pal: { source_user_id: @pal.source_user_id, target_user_id: @pal.target_user_id }
    end

    assert_redirected_to pal_path(assigns(:pal))
  end

  test "should show pal" do
    get :show, id: @pal
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @pal
    assert_response :success
  end

  test "should update pal" do
    patch :update, id: @pal, pal: { source_user_id: @pal.source_user_id, target_user_id: @pal.target_user_id }
    assert_redirected_to pal_path(assigns(:pal))
  end

  test "should destroy pal" do
    assert_difference('Pal.count', -1) do
      delete :destroy, id: @pal
    end

    assert_redirected_to pals_path
  end
end
