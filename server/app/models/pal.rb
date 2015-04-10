class Pal < ActiveRecord::Base
  
  belongs_to :source,
             :class_name  => 'User',
             :foreign_key => 'source_user_id'

  belongs_to :target,
             :class_name  => 'User',
             :foreign_key => 'target_user_id'

  validates_uniqueness_of :source_user_id, :scope => :target_user_id
  
  validate :cannot_add_self
  
  def self.generate
    Pal.create :source => User.random, :target => User.random
  end
  
  private
  
  def cannot_add_self
    if source_user_id == target_user_id
      errors.add(:target_user_id, "You cannot add yourself as a friend")
    end
  end
  
end
