class ClientService {
  async getUserProfiles() {
    return getData("/jMessage/webapi/profiles");
  }
  async getConversationMessages(thread) {
    return getData("/jMessage/webapi/messages/" + thread);
  }
  async getConversationUsers(thread) {
    return getData("/jMessage/webapi/profiles/threads/" + thread);
  }
  async getThreads() {
    return getData("/jMessage/webapi/threads/");
  }
}

function getData(url) {
  return fetch(url, {
    method: 'GET',
    mode: 'no-cors',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET',
      'Access-Control-Allow-Headers': '*',
      'Content-Type': 'application/json'
    },
    redirect: 'follow',
    referrePolicy: 'no-referrer'
  })
  .then(response => {
    if (!response.ok) {
      console.log(response.statusText);
    }
    return response.json();
  })
  .then(json => {
    return json.body;
  })
  .catch(error => {
    console.log(error);
  });
}

export default ClientService;
