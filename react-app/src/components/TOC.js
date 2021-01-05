import React, { Component } from 'react';

class TOC extends Component {
  render() {
    return (
      <nav>
        <ul>
          <li><a href="1.html">HTML</a></li>
          <li><a href="2.css">CSS</a></li>
          <li><a href="3.javascript">Javascript</a></li>
        </ul>
      </nav>
    )
  }
}

export default TOC;