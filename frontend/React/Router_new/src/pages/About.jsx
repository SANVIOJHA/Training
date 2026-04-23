// import React from 'react'

// const About = () => {
//   return (
//     <div>About</div>
//   )
// }

// export default About

import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'

const About = () => {
  const [posts, setPosts] = useState([])

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const res = await axios.get('https://jsonplaceholder.typicode.com/posts?_limit=5')
        setPosts(res.data)
      } catch (err) {
        console.log(err)
      }
    }

    fetchPosts()
  }, [])

  return (
    <div className="container">
      <h2>About (Posts)</h2>

      {posts.map(post => (
        <p key={post.id}><b>{post.title}</b></p>
      ))}

      <br />
      <Link to='/'> Go to Home</Link>
    </div>
  )
}

export default About