class Post < ActiveRecord::Base

  belongs_to :user
  belongs_to :audience
  has_many :emotions
  has_many :environments
  
  after_create :create_alerts
  
  default_scope { order(:id => :desc) }
  
  # Get most recent 20 posts
  def self.recent(id = nil)

    if id.nil? || id.to_i == -1
      Post.limit(20)
    else
      Post.where("id > ?", id).limit(100)
    end
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
      
    if [true, false].sample
      Emotion.create :user    => post.user,
                     :post    => post,
                     :emotion => Emotion::ALL.sample.last
    end
    
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
    
    # Create audience
    if rand(1..3) == 1
      post.audience = Audience.random
    end
    
    post.save!
  end
  
  private
  
  # Create an alert for each of the users subscribed to the author
  def create_alerts
    
    user.subscribers.each do |s|
      Alert.create! :post => self, :user => s
    end
    
  end

end
