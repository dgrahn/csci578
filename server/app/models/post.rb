class Post < ActiveRecord::Base

  belongs_to :user
  has_many :emotions
  
  # Get most recent 20 posts
  def self.recent
    Post.order(:id => :desc).limit(20)
  end
  
  # Gets random post
  def self.random
    Post.offset(rand(Post.count)).first
  end
  
  def author_emotions
    emotions.select { |e| e.user == user }
  end
  
  def other_emotions
    emotions.select { |e| e.user != user }
  end
  
  def self.generate
    post = Post.new
    post.text = Faker::Lorem.sentences(rand(1..6), false).join(" ")
    post.user = User.random
    
    rand(1..5).times do
      Emotion.create :user    => User.random,
                     :post    => post,
                     :emotion => Emotion::ALL.sample.last
    end
    
    post.save!

    puts "Generating: Post"
    puts "Text = #{post.text}"
    puts "User = #{post.user.name}"
  end

end
