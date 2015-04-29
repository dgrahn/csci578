
class Result
  
  def self.result(code, message)
    {:code => code, :message => message}
  end

  LOGIN_FAIL    = result(1, "Login Failed")
  LOGIN_SUCCESS = result(2, "Login Success")
   
end