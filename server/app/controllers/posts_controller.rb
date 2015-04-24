class PostsController < ApplicationController

  before_action :authenticate_user!, :except => [:show, :index, :since]
  before_action :set_post, :only => [:show, :edit, :update, :destroy]

  respond_to :html
  respond_to :json

  def index
    @posts = Post.recent
    respond_with @posts
  end
  
  # Gets the post made since the ID
  def since
    @posts = Post.recent(params[:id])
    respond_with @posts, :template => "posts/index"
  end

  def show
    respond_with(@post)
  end

  def new
    @post = Post.new
    respond_with(@post)
  end

  def edit
  end

  def create
    @post = Post.new(post_params)
    @post.user = current_user
    @post.save
    respond_with(@post)
  end

  def update
    @post.update(post_params)
    respond_with(@post)
  end

  def destroy
    @post.destroy
    respond_with(@post)
  end

  private
    def set_post
      @post = Post.find(params[:id])
    end

    def post_params
      params.require(:post).permit(:text, :user_id)
    end
end
