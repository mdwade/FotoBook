let album = {
		
}


var app = new Vue({
  el: '#app',
  components: { album },
  data: {
	  albumTitle: ""
  },
  
  methods:{
	  getSelectedAlbumName: function(albumName){		  
		  this.albumTitle = albumName;
	  }, 
  }  
  
})
