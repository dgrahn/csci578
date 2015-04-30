class Emotion < ActiveRecord::Base

  # List: http://apps.timwhitlock.info/emoji/tables/unicode
  GRIN  = ['1F601'.hex].pack("U*") # Grinning face with smiling eyes
  SMILE = ['1F603'.hex].pack("U*") # Smiling face with open mouth
  BEAM  = ['1F606'.hex].pack("U*") # Smiling face with open mouth and tightly-closed eyes
  SMIRK = ['1F60F'.hex].pack("U*") # Smirking face
  FROWN = ['1F61E'.hex].pack("U*") # Dissappointed face
  
  MAP = {
    '1F601' => GRIN,
    '1F603' => SMILE,
    '1F606' => BEAM,
    '1F60F' => SMIRK,
    '1F61E' => FROWN
  }
 
  ALL   = [
            ["Grin", GRIN],
            ["Smile", SMILE],
            ["Beam", BEAM],
            ["Smirk", SMIRK],
            ["Frown", FROWN]
          ]

  belongs_to :post
  belongs_to :user
  
  validates_presence_of :post
  validates_presence_of :user
  validates_presence_of :emotion
  
  def self.generate
    emotion = Emotion.new
    emotion.emotion = ALL.sample.first
    emotion.user = User.random
    emotion.save!
      
    puts "Generating: Emotion"
    puts "Type = #{emotion.last}"
    puts "User = #{emotion.user}"
    puts "Post = #{emotion.post.id}"
    
    post
  end

end
