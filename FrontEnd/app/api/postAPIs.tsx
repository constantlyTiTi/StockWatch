import axios from "axios"
import httpConfig from '../config/config-http.json'
import { GET_ALL_POST } from "./postURLs"
import { PostComment } from "../Interfaces/post/PostCommentRow"

export const getAllPostInfo = async () =>{
    
    const response = 
      await axios.get(GET_ALL_POST,{
        headers:httpConfig.headers
      })
  
      .then((res) => {
        const posts = res.data.all_post_comment.map((d: any) => d as PostComment)
        console.log("fe post",posts)
        return posts
  
      })
      .catch((error) => {
        console.log("fe-error",error)
        return error
      })
  
      return response
  
  }