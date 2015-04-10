module ApplicationHelper
  
  def profile_image(user, height = 50)

    return unless user

    style = "border-radius: #{height}px;"
    classes = "profile" 
    classes += " friends" if current_user && current_user.pals?(user)
    image_tag user.image,
              :class  => classes,
              :height => height,
              :style  => style,
              :title  => user.name
  end

end
