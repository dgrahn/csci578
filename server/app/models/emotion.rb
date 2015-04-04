class Emotion < ActiveRecord::Base

  # List: http://apps.timwhitlock.info/emoji/tables/unicode
  GRIN  = ['1F601'.hex].pack("U*") # Grinning face with smiling eyes
  SMILE = ['1F603'.hex].pack("U*") # Smiling face with open mouth
  BEAM  = ['1F606'.hex].pack("U*") # Smiling face with open mouth and tightly-closed eyes
  SMIRK = ['1F60F'.hex].pack("U*") # Smirking face
  FROWN = ['1F61E'.hex].pack("U*") # Dissappointed face

  belongs_to :post
  belongs_to :user

end
