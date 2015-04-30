class PostsController < ApplicationController

  before_action :authenticate_user!, :except => [:show, :index, :since, :create_mobile]
  before_action :authenticate_token!, :only => [:create_mobile]
  before_action :set_post, :only => [:show, :edit, :update, :destroy]

  respond_to :html
  respond_to :json, :only => [:create_mobile, :since]

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
  
  def create_mobile

    puts "################"
    puts params.inspect
    
    # Delete Test Post
    test_post = Post.find_by_text("Test Post")
    test_post.destroy if test_post

    # Create Post
    post = Post.new
    post.text = params[:text]

    # Set User
    if params[:anonymous] == 'true'
      post.user = User.anonymous
    else
      post.user = User.find_by_authentication_token(params[:token])
    end
    
    # Add Environments
    i = 0
    while params.has_key?("environment-#{i}-type") do
      env = Environment.new
      env.post    = post
      env.sensor  = params["environment-#{i}-type"]
      env.reading = params["environment-#{i}-reading"].to_f
      post.environments << env
      i += 1
    end
    
    # Add Emotions
    i = 0
    while params.has_key?("emotion-#{i}") do 
      emoji = Emotion.new
      emoji.emotion = Emotion::MAP[params["emotion-#{i}"]]
      emoji.user    = post.user
      post.emotions << emoji
      puts post.valid?
      puts "Emotion = #{emoji.inspect}"
      puts "ID = #{params["emotion-#{i}"]}"
      i += 1
    end
    
    User.all.each do |user|
      puts "Email = #{user.email}"
    end
    puts "User = #{post.user}"
    
    if post.save
      result = Result::POST_SUCCESS
    else
      puts "Error: #{post.errors.full_messages.join(", ")}" 
      result = Result::POST_FAIL
    end
    
    render :json => result.to_json
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
end
