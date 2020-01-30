Vue.component('messages-list', {
  template: '<div>List</div>'
})

var app = new Vue({
  el: '#app',
  template: '<messages-list />'
  data: {
    messages: [
        {id: '123', text: 'Wow'},
        {id: '23', text: 'WWWWWWWWWWWWWWWWWWWWWWW'},
        {id: '3', text: 'ok'},
    ]
  }
});