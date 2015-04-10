class Generator
  
  @@keepGoing = false
  
  def self.start
    @@keepGoing = true

    Thread.new do
      generate
    end
  end
  
  def self.is_started?
    @@keepGoing
  end
  
  def self.stop
    @@keepGoing = false
  end
  
  def self.toggle
    if is_started?
      stop
    else
      start
    end
  end
  
  def self.generate
    while @@keepGoing
      puts "##############################"
      Post.generate
      
      if rand(1..10) == 5
        User.generate
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