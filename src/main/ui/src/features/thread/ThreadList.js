import React from 'react';
import Thread from './Thread';
import './Thread.scss';

class ThreadList extends React.Component {
  render() {
    const threads = this.props.threads.map(thread =>
      <Thread
        key={thread.id}
        title={thread.threadName}
        onGetConversationMessages={() => this.props.onGetConversationMessages(thread.threadName)}
        onGetConversationUsers={() => this.props.onGetConversationUsers(thread.threadName)}
      />
    );
    return (
      <div className="thread-list">
        <div className="thread-list-header">Thread</div>
        {threads}
      </div>
    )
  }
}

export default ThreadList;
