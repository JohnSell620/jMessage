import React from 'react';

class Conversation extends React.Component {
  render() {
    const messages = this.props.messages.map(message =>
      <Message key={message._links.self.href} message={message}/>
    );
    return (
      <div className="conversation">
        {messages}
      </div>
    )
  }
}
