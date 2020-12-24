import React from 'react';

class Message extends React.Component {
  render() {
    return(
      <div className="message-content">
        <span className="message-author">{this.props.author}</span>
        <span className="message-time">{this.props.created}</span>
        <div className="message-text">
          {this.props.message}
        </div>
      </div>
    )
  }
}

export default Message;
