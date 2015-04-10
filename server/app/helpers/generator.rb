class Generator
  
  @keepGoing = false
  
  def is_generating?
    @keepGoing
  end
  
  def toggle
    @keepGoing = @keepGoing ? false : true
    puts "Keep Going = #{@keepGoing}"
    generate
  end
  
  def generate
    while is_generating?
      puts "#################################"
      Post.generate
      
      if rand(1..10) == 5
        User.generate
      else
        puts "Not generating user"
      end
      
      delay = rand(1..4)
      puts "Delay: #{delay}"
      sleep delay 
    end
  rescue Exception => e
    puts e.message
    puts e.backtrace.join("\n")
  end #generate

end #Generator