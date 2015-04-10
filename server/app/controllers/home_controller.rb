class HomeController < ApplicationController

  def index
    @posts = Post.recent
  end
  
  def generate
    Generator.toggle
    redirect_to root_url
  end

end
