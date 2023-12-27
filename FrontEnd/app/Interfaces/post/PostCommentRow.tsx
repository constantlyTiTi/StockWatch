export interface PostComment{
    id:string,
    poster:string,
    avatar:string,
    message:string,
    datetime:string,
    replyTo:string|undefined,
    title:string|undefined,
    parentPostId:string|undefined
}

