<template>
  <div v-if="user" class="dashboard">
    <h1>Welcome, {{ user.user_name}}!</h1>
    <button @click="logout" class="logout-button">🏠 Return Home</button>

    <!-- Create Post -->
    <section class="create-post">
      <h2>Create a New Post</h2>
      <textarea v-model="newPost.content" placeholder="Write something..."></textarea>
      <input type="file" @change="onImageSelected" />
      <button @click="createPost">Post</button>
    </section>

    <!-- Posts List -->
    <section class="posts">
      <h2>All Posts</h2>
      <div v-for="post in posts" :key="post.postId" class="post-card">
        
        <div class="post-header">
          <strong>{{ post.user_Id }}</strong> · {{ formatDate(post.createdAt) }}
        </div>
        <!-- If editing, show editable textarea -->
        <div v-if="editingPostId === post.postId">
            <textarea v-model="editContent"></textarea>
            <button @click="submitEdit(post.postId)">Save</button>
            <button @click="cancelEdit">Cancel</button>
        </div>

        <!-- Else show the post content -->
        <p v-else>{{ post.content }}</p>
        <img v-if="post.image" :src="post.image" alt="Post Image" class="post-image" />

        <!-- Only show delete for own posts -->
        <div v-if="post.user_Id?.userId === user.userId" class="post-actions">

            <button @click="deletePost(post.postId)">Delete</button>
            <button @click="startEdit(post)">Edit</button>

        </div>
                <!-- Comments Section -->
        <div class="comments">
            <h4>Comments</h4>

            <!-- List of comments for this post -->
            <div v-for="comment in comments[post.postId] || []" :key="comment.commentId" class="comment">
                <strong>{{ comment.user?.userName || 'Unknown' }}</strong>: {{ comment.content }}
            </div>
            <!-- New comment input -->
            <input v-model="newComment[post.postId]" placeholder="Write a comment..." />
            <button @click="submitComment(post.postId)">Send</button>
        </div>

      </div>
    </section>
  </div>

  <div v-else class="loading">
    <p>Loading user info...</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
//Comments
const comments = ref({})          // Stores comments keyed by postId
const newComment = ref({})        // Stores new comment input per post

// Safe user load
const user = ref(null)
const posts = ref([])
const newPost = ref({ content: '', image: null })

onMounted(() => {
  const storedUser = localStorage.getItem('user')
  if (storedUser) {
    user.value = JSON.parse(storedUser)
    console.log("entered :"+user.value.userId)
    fetchPosts()
  } else {
    console.warn('⚠️ No user found in localStorage.')
  }
})
//logout
const logout = () => {
  localStorage.removeItem('user')
  localStorage.removeItem('phoneNum')
  localStorage.removeItem('userId')

  // Manually redirect to home page
  window.location.href = '/'
}
// Load all posts
const fetchPosts = async () => {
  try {
    const res = await axios.get('http://localhost:8081/api/posts')
    posts.value = res.data

    // Fetch comments for each post
    for (const post of res.data) {
      fetchComments(post.postId)
    }
  } catch (err) {
    console.error('❌ Failed to load posts:', err)
  }
}
//comments
//get  comments
const fetchComments = async (postId) => {
  try {
    const res = await axios.get(`http://localhost:8081/api/comments/post/${postId}`)
    comments.value[postId] = res.data
  } catch (err) {
    console.error(`❌ Failed to load comments for post ${postId}:`, err)
  }
}
//submit comment
const submitComment = async (postId) => {
  try {
    const content = newComment.value[postId]
    if (!content) return

    await axios.post('http://localhost:8081/api/comments', {
      content,
      post: { postId },
      user: { userId: user.value.userId }
    })

    newComment.value[postId] = ''
    fetchComments(postId)
  } catch (err) {
    console.error('❌ Failed to submit comment:', err)
  }
}
// Image selection
const onImageSelected = (e) => {
  newPost.value.image = e.target.files[0]
}

// Post creation
const createPost = async () => {
  try {
    const form = new URLSearchParams()
    const phoneNum = localStorage.getItem('phoneNum')  
    console.log(phoneNum);
    form.append('content', newPost.value.content)
    form.append('phoneNumber', phoneNum) 

    await axios.post('http://localhost:8081/api/posts', form, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })

    newPost.value.content = ''  // Clear input after success
    await fetchPosts()          // Refresh posts list
  } catch (err) {
    console.error('❌ Failed to create post:', err)
  }
}

//edit post
const editingPostId = ref(null)
const editContent = ref('')

const startEdit = (post) => {
  editingPostId.value = post.postId
  editContent.value = post.content
}

const cancelEdit = () => {
  editingPostId.value = null
  editContent.value = ''
}

const submitEdit = async (postId) => {
  try {
    const form = new URLSearchParams()
    form.append('content', editContent.value)

    await axios.put(`http://localhost:8081/api/posts/${postId}`, form, {
      headers: { 'Content-Type': 'text/plain' }
    })

    editingPostId.value = null
    await fetchPosts()
  } catch (err) {
    console.error('❌ Failed to update post:', err)
  }
}

// Delete post
const deletePost = async (postId) => {
  try {
    await axios.delete(`http://localhost:8081/api/posts/${postId}`)
    await fetchPosts()
  } catch (err) {
    console.error('❌ Failed to delete post:', err)
  }
}

// Format timestamp
const formatDate = (iso) => {
  return new Date(iso).toLocaleString()
}
</script>

<style scoped>
.dashboard {
  max-width: 800px;
  margin: auto;
  padding: 2rem;
}
textarea {
  width: 100%;
  height: 80px;
  margin-bottom: 10px;
}
.post-card {
  border: 1px solid #ccc;
  border-radius: 6px;
  padding: 1rem;
  margin-bottom: 1.5rem;
}
.post-header {
  font-weight: bold;
  margin-bottom: 0.5rem;
}
.post-image {
  max-width: 100%;
  margin-top: 0.5rem;
}
.post-actions {
  margin-top: 0.5rem;
}
.loading {
  text-align: center;
  margin-top: 4rem;
  font-size: 1.2rem;
}
</style>
