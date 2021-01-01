
class ClientService {
  async getUserProfiles() {
    return getData("/webapi/profiles/");
  }
  async getConversationMessages(thread) {
    return getData("/webapi/messages/" + thread + "/");
  }
  async getConversationUsers(thread) {
    return getData("/webapi/threads/" + thread + "/users");
  }
  async getThreads() {
    // TODO create threads table in MySQL
    return getThreadData("/webapi/threads/");
  }
}

function getData(url) {
  return fetch(url, {
    method: 'GET',
    mode: 'cors',
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
    console.log(json);
    return json;
  })
  .catch(error => {
    console.log(error);
  });
}

function getThreadData(url) {
  return [
    {
      id: 1,
      title: "Science",
      users: [
        {
          id: 2,
          profileName: "john5"
        },
        {
          id: 3,
          profileName: "jim2"
        }
      ]
    },
      {
        id: 2,
        title: "Sports",
        users: [
          {
            id: 1,
            profileName: "pat7"
          },
          {
            id: 3,
            profileName: "jim2"
          }
        ]
      },
        {
          id: 3,
          title: "Games",
          users: [
            {
              id: 2,
              profileName: "john5"
            },
            {
              id: 4,
              profileName: "jack2"
            }
          ]
        },
          {
            id: 4,
            title: "Life",
            users: [
              {
                id: 1,
                profileName: "pat7"
              },
              {
                id: 2,
                profileName: "john5"
              }
            ]
          }
  ];
}

export default ClientService;
