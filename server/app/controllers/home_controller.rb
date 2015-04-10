class HomeController < ApplicationController
  
  def initialize
    super
    @generator = Generator.new
  end

  def index
    @posts = Post.recent
  end
  
  def random
    Thread.new do
      @generator.toggle
    end
    
    unless @generator.is_generating?
      @generator = Generator.new
    end
    
    text = "Generating " + (@generator.is_generating? ? "Started" : "Stopped")
    render :text => text
  end

end
