module ApplicationHelper

  def create_emoji(character)
    #character.to_i(16)
    [character.hex].pack("U*")
  end

end
