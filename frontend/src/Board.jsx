import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Board() {
const baseurl = 'http://localhost:8080';
  const [posts, setPosts] = useState([]);
  const [replies, setReplies] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [nowPostId, setNowPostId] = useState(0);
  const [input, setInput] = useState({
    content: '',
    password: '',
    title: '',
  })
  const [Replyinput, setReplyInput] = useState({
    replyContent: '',
    password: '',
    postId: '',
  });

  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    const response = await axios.get(`${baseurl}/api/board/posts`);
    setPosts(response.data);
  };

  const createPost = async () => {
    const response = await axios.post(`${baseurl}/api/board/create`, input);
    setPosts([...posts, response.data]);
    setInput({ title: '', content: '', password: '' });
  };

  const deletePost = async (postId, password) => {
    var passwd = window.prompt("비밀번호를 입력하세요");
    axios.get(`${baseurl}/api/board/delete/${postId}?password=${passwd}`)
        .then(response => {
            alert(response.data);
        })
        .catch(error => {
            console.error(error);
        });
  };

  const updatePost = async (postId) => {
    var title = window.prompt("제목을 입력하세요.");
    var content = window.prompt("내용을 입력하세요.");
    var passwd = window.prompt("비밀번호를 입력하세요");
    axios.post(`${baseurl}/api/board/update`, { postId: postId, title:title , content: content, password: passwd })
    .then(response => {
        alert(response.data);
    })
    .catch(error => {
        console.error(error);
    });
  };

  const fetchReplies = async (postId) => {
    const response = await axios.get(`${baseurl}/api/reply/${postId}`);
    setReplies(response.data);
  };

  const viewReplyModal = (postId) => { 
    setIsModalOpen(true);
    setNowPostId(postId);
    }
    
  const createReply = async (nowPostId) => {
    const response = await axios.post(`${baseurl}/api/reply/create/${nowPostId}`, Replyinput);
    setReplies([...replies, response.data]);
    fetchReplies(response.data.postId);
    setReplyInput({ replyContent: '', password: '', postId: nowPostId});
    
}

  const deleteReply = async (replyId, postId) => {
    var passwd = window.prompt("비밀번호를 입력하세요");
    axios.get(`${baseurl}/api/reply/delete/${replyId}?password=${passwd}`)
    .then(response => {
        alert(response.data);
        fetchReplies(postId);
    })
    .catch(error => {
        console.error(error);
    });
  };

    const modalClose = () => { setIsModalOpen(false); }

    // 타임 스탬프 포맷 변경
  const formatDate = (timestamp) => {
    const dateObject = new Date(timestamp);
    
    const year = dateObject.getFullYear();
    const month = ('0' + (dateObject.getMonth() + 1)).slice(-2); // Months are zero-based
    const day = ('0' + dateObject.getDate()).slice(-2);
    const hours = ('0' + dateObject.getHours()).slice(-2);
    const minutes = ('0' + dateObject.getMinutes()).slice(-2);
    const seconds = ('0' + dateObject.getSeconds()).slice(-2);
  
    const formattedDate = `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;
    return formattedDate;
  };

  return (
    <div>
      <div>
        <h1>익명게시판</h1>
        <div>
        <h3>게시글 작성</h3>
            <input type="text" value={input.title} onChange={(e) => setInput({...input, title: e.target.value})} placeholder="Title" />
            <input type="text" value={input.content} onChange={(e) => setInput({...input, content: e.target.value})} placeholder="Content" />
            <input type="password" value={input.password} onChange={(e) => setInput({...input, password: e.target.value})} placeholder="Password" />
            <button onClick={createPost}>게시글 작성</button>
            <br/>
        </div>
        <table className="post_table">
            <thead>
                <th>제목</th>
                <th>내용</th>
                <th>작성일</th>
                <th>CRUD</th>
            </thead>
        {posts.map((post) => (
          <tr key={post.id}>
            <td>
            <h2>{post.title}</h2>
            </td>
            <td>
            <p>{post.content}</p>
            </td>
            <td>
            <p>{formatDate(post.created_at)}</p>
            </td>
            <td>
            <button onClick={() => deletePost(post.postId)}>게시글 삭제</button>
            <button onClick={() => updatePost(post.postId)}>게시글 수정</button>
            <button onClick={() => fetchReplies(post.postId)}>댓글 보기</button>
            <button onClick={() => viewReplyModal(post.postId)}>댓글 작성</button>
            </td>
          </tr>
        ))}
        </table>
        <h2>댓글</h2>
        <table className="reply_table">
        {replies.map((reply) => (
          <tr key={reply.id}>
            <td><h4>{reply.replyContent}</h4></td>
            <td><p>{formatDate(reply.replyCreatedAt)}</p></td>
            <td><button onClick={() => deleteReply(reply.replyId, reply.postId)}>Delete</button></td>
          </tr>
        ))}
        </table>

      </div>
      {isModalOpen && (  
    <div className='modal__background'>
    <div class="modal__box">
    <button onClick={modalClose}>X</button>
    {/* <input type="hidden" value={input.postId} onChange={(e) => setInput({...input, postId: e.target.value})} /> */}
        <textarea type="text" value={Replyinput.replyContent} onChange={(e) => setReplyInput({...Replyinput, replyContent: e.target.value})} placeholder="Content" />
        <input type="password" value={Replyinput.password} onChange={(e) => setReplyInput({...Replyinput, password: e.target.value})} placeholder="Password" />
        <button onClick={() => createReply(nowPostId)}>댓글 작성</button>
      </div>
      </div>
      )}
    </div>
  );
}

export default Board;

