import React from 'react';
import Message from './Message';

class Conversation extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    const messages = this.props.messages.map(message =>
      <Message
        key={message.id}
        message={message.message}
        author={message.author}
        created={message.created}
      />
    );
    return (
      <div className="conversation">
        <div className="conversation-header">Conversation</div>
        {messages}
        <div className="enter-text-window">Enter text:</div>
      </div>
    )
  }
}

export default Conversation;
