package metiers;

import java.util.List;

import model.Album;
import model.Image;

public class AlbumUtils {

	public static boolean checkIfEmpty(String data) 
	{
		Boolean isNotEmpty = false;
		
		if (data.length() == 0 || data.length() == 1) 
		{
			isNotEmpty = true;
		}
			
		return isNotEmpty;
	}
	
	public static Boolean checkIfAlbumExist(String albumName, List<Album> listUserAlbum) {
		Boolean exist = true;
		
		for(Album a : listUserAlbum) {
			if(!albumName.equals(a.getName())) {
				exist = false;
			}
		}
		
		return exist;
	}
	
	public static String albumBloc(Album a, String uploadPath) {
		uploadPath +=  a.getId();
		int albumNameWithoutSpace = a.getId();
		System.out.println(uploadPath);
		String msg1 = "<div class=\"w3-row\">\n" + 
		"				<div class=\"w3-col l10\">\n" + 
		"					<h4 id=\"albumTitle\">"+a.getName()+"</h4>\n" + 
		"				</div>\n" + 
		"				<div class=\"w3-col l1 w3-right\" id=\"addPhotoBtn\">\n" + 
		"					<button class=\"w3-btn w3-tiny w3-orange w3-round w3-right\" onclick=\"document.getElementById('addPhoto').style.display='block'\">\n" + 
		"						Ajouter une photo\n" + 
		"					</button>\n" + 
		"				</div>\n" + 
		"			</div>\n" + 
		"			<div class=\"w3-row w3-margin-top\">\n";
		
		String msg2 = "";
		
		if(a.getImages().size() == 0) {
			msg2 += "Album vide";
		}
		else {
			for(Image i: a.getImages()) {			
				msg2 +="<div class=\"w3-col l1 w3-animate-left imgDiv\" style=\"width:200px; height:200px\">"
							+ "<div class=\"w3-card w3-container-display\" style=\"width:100%; height:100%\">\n" 
								+ "<div class=\"w3-display-bottomright w3-tiny w3-padding w3-round\" style=\"margin:10px; background-color:rgba(255,255,255,0.9); z-index:1 \">"															
								+ " <a href=\"#\" class=\"detailImage\" data-id="+i.getId()+'-'+i.getAlbum().getId()+"> "+i.getTitle()+" </a> "
								+ ""
								+ "<a href=\"/FotoBook/delete_image?id="+i.getId()+"\" onclick=\"return confirm('Voulez vous supprimer cette photo ?')\">"
								+ "<i class=\"fa fa-trash\"></i> "
								+ "</a>"
								+ ""				
								+ "<a href=\"#\" class=\"editImage\" data-id="+i.getId()+">"
								+ "<i class=\"fa fa-edit\"></i> "								
								+ "</a>"
								+ "</div>"																							
								+ "<a href="+uploadPath.substring(uploadPath.lastIndexOf("static"), 
										uploadPath.length())+"/"+i.getImageFile()+" data-lightbox="+albumNameWithoutSpace+" data-title="+i.getTitle()+">"
										+ "<img class=\"imgAlbum\" "
								+ 		"src="+uploadPath.substring(uploadPath.lastIndexOf("static"), 
										uploadPath.length())+"/"+i.getImageFile()+" style=\"width:100%; height:100%\" data-id="+i.getId()+">"
								+ "</a>"							
							+ "</div>"						
						+ "</div>";
			}
		}
				
		String msg3 = "</div>\n" + 
				"			\n" + 
				"			<div id=\"addPhoto\" class=\"w3-modal\">\n" + 
				"				<div\n" + 
				"					class=\"w3-modal-content w3-animate-top w3-card-4 w3-round w3-padding-large\"\n" + 
				"					style=\"width: 35%\">\n" + 
				"					<header class=\"w3-container\">\n" + 
				"						<span onclick=\"document.getElementById('addPhoto').style.display='none'\"\n" + 
				"							class=\"w3-button w3-display-topright w3-hover-red\">&times;</span>\n" + 
				"						<div class=\"w3-row\">\n" + 
				"							<div class=\"w3-col l1\" style=\"margin-top: 10px\">\n" + 
				"								<i class=\"fa fa-photo w3-xxlarge\"></i>\n" + 
				"							</div>\n" + 
				"							<div class=\"w3-col l10\">\n" + 
				"								<h3>Ajouter une photo à "+a.getName()+"</h3>\n" + 
				"								<p style=\"font-size: 12px; color: orange;\"></p>\n" + 
				"							</div>\n" + 
				"						</div>\n" + 
				"					</header>\n" + 
				"					<hr>\n" + 
				"					<div class=\"w3-container\">\n" + 
				"						<form action=\"/FotoBook/add_image\" method=\"post\" enctype=\"multipart/form-data\">\n" + 
				"							<div class=\"w3-row-padding\">\n" + 
				"								<div class=\"w3-col s12\">\n" + 
				"									<input type=\"text\" class=\"imageTitle\" name=\"imageTitle\" placeholder=\"Tapez la titre...\" required><br><br><br>\n" + 
				"								</div>\n" + 
				"								\n" + 
				"								<div class=\"w3-col s12\">\n" + 
				"									<textarea name=\"description\" placeholder=\"Tapez la description...\" class=\"w3-input w3-border w3-round\" required></textarea><br><br>\n" + 
				"								</div>\n" + 
				"								\n" + 
				"								<div class=\"w3-col s12\">\n" + 
				"									<textarea name=\"keyWords\" placeholder=\"Tapez les mots clés...\" class=\"w3-input w3-border w3-round\" required></textarea><br><br>\n" + 
				"								</div>\n" + 
				"								\n" + 
				"								<div class=\"w3-col s12\">\n" + 
				"									<input type=\"hidden\" name=\"idAlbum\" value="+a.getId()+">\n" + 
				"								</div>\n" + 
				"								\n" + 
				"								<div class=\"w3-col s12\">\n" + 
				"									<input type=\"file\" name=\"imageFile\" id=\"file\" class=\"inputfile\"/>\n" + 
				"									<label for=\"file\"><i class=\"fa fa-photo\"></i> Choisir une image</label>\n" + 
				"								</div>\n" + 
				"								\n" + 
				"								<style>\n" + 
				"									.imageTitle{\n" + 
				"										width: 100% !important;\n" + 
				"										border-radius: 5px 5px 5px 5px !important;\n" + 
				"										padding: 14px;\n" + 
				"									}\n" + 
				"									\n" + 
				"									.inputfile {\n" + 
				"										width: 0.1px;\n" + 
				"										height: 0.1px;\n" + 
				"										opacity: 0;\n" + 
				"										overflow: hidden;\n" + 
				"										position: absolute;\n" + 
				"										z-index: -1;\n" + 
				"									}\n" + 
				"									\n" + 
				"									.inputfile + label {\n" + 
				"									    font-size: 1.25em;\n" + 
				"									    padding: 10px;\n" + 
				"									    display: inline-block;\n" + 
				"									}\n" + 
				"									\n" + 
				"									.inputfile:focus + label,\n" + 
				"									.inputfile + label:hover {\n" + 
				"									    background-color: orange;\n" + 
				"									    border-radius: 5px;\n" + 
				"									}\n" + 
				"									\n" + 
				"									.inputfile + label {\n" + 
				"										cursor: pointer; /* \"hand\" cursor */\n" + 
				"									}\n" + 
				"									\n" + 
				"									\n" + 
				"									.imgAlbum {"+
				"	  									border: 1px solid #ddd;"+
				"	  									border-radius: 4px;"+
				"	  									padding: 5px;"+
				"	  									width: 150px;"+
				"										transition: 1s;"+
				"									}"+
				"									.imgAlbum:hover {"+
				"	 	 								box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);"+
				"										cursor:pointer;"+
				"										filter: grayscale(50%);"+
				"										transform: scale(1.1);"+
				"									}"+
				"								</style>\n" + 
				"							</div>\n" + 
				"							<br><br>\n" + 
				"							<div class=\"w3-right w3-padding\">\n" + 
				"								<input type=\"submit\" value=\"Valider\"\n" + 
				"									class=\"w3-btn w3-round w3-orange\">\n" + 
				"							</div>\n" + 
				"						</form>\n" + 
				"					</div>\n" + 
				"				</div>\n" + 
				"			</div>";		
		
		
		return msg1+msg2+msg3;
	}

}
