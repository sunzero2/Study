import React, { Component } from 'react';

class Subject extends Component {
  render() {
    return (
      <header>
        <h1><a onClick={(e) => {
          e.preventDefault();
          this.props.onChangePage();
        }}href="/">{this.props.title}</a></h1>
        {this.props.sub}
      </header>
    )
  }
}

export default Subject;