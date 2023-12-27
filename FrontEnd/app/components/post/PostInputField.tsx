import { Input } from "@mui/material";
import useWebSocket from "react-use-websocket";

const PostInputField = () =>{

    // const { sendMessage, lastMessage, readyState } = useWebSocket(socketUrl);

    return(
        <Input aria-label="Demo input" multiline placeholder="Type something…" />
    )
}

export default PostInputField