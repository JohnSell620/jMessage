import React from 'react';

class Thread extends React.Component {
  render() {
    return (
      <div className="thread-inlist">
        <div className="thread-title" onClick={() => this.getConversationAndUsers()}>
          {this.props.title}
        </div>
      </div>
    );
  }
  getConversationAndUsers() {
      console.log("TODO: Populate conversation and user windows with data...");
  }
}

export default Thread;
