import {
  Container,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import Paper from "@mui/material/Paper";
import { useEffect, useState, useCallback, useReducer } from "react";
import PostedMessagesRow from "./PostedMessagesRow";
import {TableRowProps} from '../../Interfaces/table/TableRowProps'
import useWebSocket, { ReadyState } from 'react-use-websocket';
import { PostComment } from "@/app/Interfaces/post/PostCommentRow";
import { getAllPostInfo } from "@/app/api/postAPIs";
import { postReducer } from "@/app/reducers/postReducer";
import { GET_ALL_POSTS_INFO } from "@/app/actions/postActionsTypes";

const PostList = () => {

  const [postComments, setPostComments] = useState<PostComment[]>([]);
  const [state, dispatch] = useReducer(postReducer, {all_post_comments:[]});

  const fetchData = useCallback(() => {

    async () => {
      getAllPostInfo().then((res)=>{
        dispatch({
          type: GET_ALL_POSTS_INFO,
          payload: res
        })
        setPostComments(state.all_post_comments)
      })
      .catch((error)=>console.log(error))
    }
  },[]) 

  useEffect(() => {
    fetchData()
  }, []);

  return (
    <Container component={Paper}>
      {
        postComments!! && postComments.map(post =>{

          return <PostedMessagesRow {...post}/>
})
      }
      
    </Container>
  );
};

export default PostList;
