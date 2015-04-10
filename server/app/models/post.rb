class Post < ActiveRecord::Base

  belongs_to :user
  has_many :emotions
  has_many :environments
  
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
    
    # Create emotions
    rand(0..5).times do
      Emotion.create :user    => User.random,
                     :post    => post,
                     :emotion => Emotion::ALL.sample.last
    end
    
    # Create environments
    rand(0..2).times do
      Environment.create :post    => post,
                         :sensor  => Environment::ALL.sample,
                         :reading => rand() * 100
    end
    
    post.save!
  end

end
