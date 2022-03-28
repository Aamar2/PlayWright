package com.infa.utils;

import java.util.List;

public class JSCommands {
	// TODO: those commands may not work if multiple mappings are opened.
	
	/**
	 * hover edge between sourceTx and targetTx 
	 * @param fromTx
	 * @param toTx
	 * @return
	 */
	public String hoverEdge(String fromTx, String toTx) {
		String selectNodeJS = "var canvas = $('.canvasContainer');\n"
				+ "var template = $.getWidget(canvas, 'templateCanvas');\n" + "var arrEdges = template.getAllEdges();\n"
				+ "var len = arrEdges.length;\n" + "var count=0;\n" +

				"for (var i = 0; i<len; i++) {\n" +

				"if(arrEdges[i].object.$fromTransformation.name==='%s' && arrEdges[i].object.$toTransformation.name==='%s'){\n"
				+ "count=i; \n" + "break;\n" + "}\n" + "}\n"
				+ "arrEdges[count].drawControl.dispatchEvent('mouseover');";
		String code = String.format(selectNodeJS, fromTx, toTx);
		return code;

	}
	
	
	
	/**
	 * @param fromTx - from TX
	 * @param toTx  - To Tx
	 * @return true/false if recommendation is present
	 */
	public String isRecommendationIconPresent(String fromTx, String toTx) {
		String selectNodeJS = "var canvas = $('.canvasContainer');\n"
				+ "var template = $.getWidget(canvas, 'templateCanvas');\n" + "var arrEdges = template.getAllEdges();\n"
				+ "var len = arrEdges.length;\n" + "var count=0;\n" +
			
				/*find edge between source and target*/
				"for (var i = 0; i<len; i++) {\n" +

				"if(arrEdges[i].object.$fromTransformation.name==='%s' && arrEdges[i].object.$toTransformation.name==='%s'){\n"
				+ "count=i; \n" + "break;\n" + "}\n" + "}\n" + "var edge = arrEdges[count];\n"
				+ "var arrChildren = edge.drawControl.children;\n" + "var lenChildren = arrChildren.length;\n"
				+ "var isRecommendationPresent=false;\n" +

				/*verify if there is recommendation icon on the edge*/
				"for(var j=0 ; j<lenChildren ; j++) {\n" + "if(arrChildren[j].name==='RecommendationIcon'){\n"
				+ "isRecommendationPresent=true; \n" + "break;\n" + "}\n" + "}\n" + "return isRecommendationPresent;\n";
		String code = String.format(selectNodeJS, fromTx, toTx);
		return code;

	}
	
	
	public String addJavaTxCode(String javacode, int header) {

		String code =
					"var editor = $('.CodeMirror')[0].CodeMirror;\n" +
				    "var doc = editor.getDoc();\n" +
					"var headers = doc.getAllMarks();\n" +
					"doc.replaceRange('"+ javacode +"', {line: headers[" + header + "].find().from.line+1, ch:0});";
		return code;

	}
	public String addPythonTxCode(List<String> pythoncode, int header) {

		String code =
					"var editor = $('.CodeMirror')[0].CodeMirror;\n" +
				    "var doc = editor.getDoc();\n" +
					"var headers = doc.getAllMarks();\n";
		
		int index = 1;
		for(String line: pythoncode) {
					code += "doc.replaceRange('"+ line +"\\n', {line: headers[" + header + "].find().from.line+" + index +", ch:0});";
					index++;
		}
	
		return code;

	}
	public String addQueryInSQLTx(String query) {
		String code =
				"var _editor = document.querySelectorAll(\"div.CodeMirror\")[0].CodeMirror;" +
						"_editor.setValue('" + query + "');" +
						"$(_editor).click()";
		return code;
	}
    public String selectNode(String nodeName) {
        String selectNodeJS =
                "var canvas = $('.canvasContainer');\n" +
                        "var template = $.getWidget(canvas, 'templateCanvas');\n" +
                        "var nodeArray = template.getAllNodes();\n" +
                        "var arrayLength = nodeArray.length;\n" +
                        "var node;" +
                        "for (var i = 0; i < arrayLength; i ++) {\n" +
                        "   if(nodeArray[i].object.name === '%s') {\n" +
                        "       node = nodeArray[i];\n" +
                        "   }\n" +
                        "}\n" +
                        "template.setSelection(node, true, true, true);";
        selectNodeJS = String.format(selectNodeJS, nodeName);
        return selectNodeJS;
    }

	public String NavigateSourceField(String sourcefieldname){

		String selSrcEle =
				"var rootElementSrc = document.getElementsByClassName('fieldMapperSrc');"
				+ "var treeSrc = $.ui.fancytree.getNode(rootElementSrc[0].children[2].children[0].children[2].children[0]);"
				+ "function findChildNode(node, name){"
				+ "		var childNode = null;"
				+ "			if(node.children && node.children.length > 0 ){"
				+ "					node.setExpanded();"
				+ "					for(var i=0;i<node.children.length;i++){"
				+ "							var foundNode = node.children[i].title === name;"
				+ "							if(foundNode)"
				+ "									childNode = node.children[i];"
				+ "					}"
				+ "			}"
				+ "			return childNode;"
				+ "};"
				+ "function findNodeHierarchy(tree, nodePath){"
				+ "		var paths = nodePath.split('.');"
				+ "		var level = 0;"
				+ "		var currentNode = tree;"
				+ "		while(true){"
				+ "				if(level === paths.length)"
				+ "						break;"
				+ "				var node = findChildNode(currentNode, paths[level]);"
				+ "				if(node){"
				+ "					currentNode = node;"
				+ "				}"
				+ "				level++;"
				+ "		}"
				+ "		return currentNode;"
				+ "}"
				+ "var nodeSrc=findNodeHierarchy(treeSrc,'"+sourcefieldname+"');"
				+ "$(nodeSrc.tr).click();";

		return selSrcEle;

	}

	public String NavigateTargetField(String targetfieldname){

		String selTgtEle =
				"var rootElementTgt = document.getElementsByClassName('fieldMapperTgt');"
				+ "var treeTgt = $.ui.fancytree.getNode(rootElementTgt[0].children[2].children[0].children[2].children[0]);"
				+ "function findChildNode(node, name){"
				+ "		var childNode = null;"
				+ "		if(node.children && node.children.length > 0 ){"
				+ "				node.setExpanded();"
				+ "				for(var i=0;i<node.children.length;i++){"
				+ "						var foundNode = node.children[i].title === name;"
				+ "						if(foundNode)"
				+ "								childNode = node.children[i];"
				+ "				}"
				+ "		}"
				+ "		return childNode;"
				+ "};"
				+ "function findNodeHierarchy(tree, nodePath){"
				+ "		var paths = nodePath.split('.');"
				+ "		var level = 0;"
				+ "		var currentNode = tree;"
				+ "		while(true){"
				+ "				if(level === paths.length)"
				+ "				break;"
				+ "				var node = findChildNode(currentNode, paths[level]);"
				+ "				if(node){"
				+ "						currentNode = node;"
				+ "				}"
				+ "				level++;"
				+ "		}"
				+ "		return currentNode;"
				+ "}"
				+ "var nodeTgt=findNodeHierarchy(treeTgt,'"+targetfieldname+"');"
				+ "$(nodeTgt.tr).click();";

		return selTgtEle;

	}

	public String LinkNodes(String srcNode, String tgtNode) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
	            "var template = $.getWidget(canvas, 'templateCanvas');\n" +
	            "var nodeArray = template.getAllNodes();\n" +
	            "var sourceAnchor = null;\n" +
	            "var targetAnchor = null;\n" +
	            "var arrayLength = nodeArray.length;\n" +
	            "for (var i = 0; i < arrayLength; i ++) {\n" +
	            "    if(nodeArray[i].object.name === '%s') {\n" +  // sourceTransformationName
	            "        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
	            "    } else if (nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
	            "        targetAnchor = nodeArray[i].getInputAnchors()[0];\n" +
	            "    }\n" +
	            "}\n" +
	            "template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);";
		sLinkNodeJS = String.format(sLinkNodeJS, srcNode, tgtNode);
		return sLinkNodeJS;
	}


	public String LinkJoinerNodes(String srcNode, String tgtNode) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
						"var template = $.getWidget(canvas, 'templateCanvas');\n" +
						"var nodeArray = template.getAllNodes();\n" +
						"var sourceAnchor = null;\n" +
						"var targetAnchor = null;\n" +
						"var arrayLength = nodeArray.length;\n" +
						"for (var i = 0; i < arrayLength; i ++) {\n" +
						"    if(nodeArray[i].object.name === '%s' && i!= 3) {\n" +  // sourceTransformationName
						"        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
						"    } else if (nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
						"        targetAnchor = nodeArray[i].getInputAnchors()[0];\n" +
						"    }\n" +
						"}\n" +
						"template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);" +
						"template.graphController.addEdge(\"testLink\",  nodeArray[3].getOutputAnchors()[0], nodeArray[2].getInputAnchors()[1]);";
		sLinkNodeJS = String.format(sLinkNodeJS, srcNode, tgtNode);
		return sLinkNodeJS;
	}


	/**
	 * @param sourceTxName Tx name of source
	 * @param groupName groupName Anchor of Tx Input Node, use the exact name of the group.
	 * @param txName Tx Name
	 * @return String, a javascript code to connect source with any group of Transformation
	 * use "+ New group" to connect to new Group of Tx and create a new Group in Tx
	 */
	public String LinkSourcesWithTxInputGroup(String sourceTxName, String txName, String groupName) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
						"var template = $.getWidget(canvas, 'templateCanvas');\n" +
						"var nodeArray = template.getAllNodes();\n" +
						"var sourceAnchor = null;\n" +
						"var targetAnchor = null;\n" +
						"var arrayLength = nodeArray.length;\n" +
						"for (var i = 0; i < arrayLength; i ++) {\n" +
						"    if (nodeArray[i].object.name === '%s') {\n" +  //sourceTransformationName
						"        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
						"    }\n" +
						"    else if(nodeArray[i].object.name === '%s') {\n" +  //targetTransformationName
                        "		var anchorArray = nodeArray[i]._inputAnchors;\n" +
						"		var inputAnchorLength = anchorArray.length;\n" +
						"		for(var j=0; j < inputAnchorLength;j++){\n"+
						" 			if(anchorArray[j].object.group.name==='%s') {\n"+
						"				 targetAnchor = nodeArray[i].getInputAnchors()[j];\n" +
						"    			}\n" +
						"   		 }\n" +
						"   	}\n" +
						"}\n" +
						"if(targetAnchor.isCreateButton){\n"+
							"var undoChange = infaw.template.TemplateUIUtils.instance().createUndoChange(targetAnchor.node.object.$$fcParent, \'add group\');\n"+
							"var res = sourceAnchor._getTargetAnchor(targetAnchor,undoChange);\n"+
							"res.done(function(r){template.graphController.addEdge(\"testLink\", sourceAnchor, r)})\n"+
						".always(function() {\n"+
							"if (undoChange) {\n"+
							  	"if (undoChange.getChanges().length) {\n"+
									"undoChange.end();\n"+
								"} else {\n"+
									"undoChange.discard();\n"+
										"}\n"+
								"}\n"+
									"});\n"+
						"}else{\n"+
							"template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);\n"+
						"};";
		sLinkNodeJS = String.format(sLinkNodeJS,sourceTxName, txName, groupName);
		return sLinkNodeJS;
	}

	
	

	public String LinkRouterNodes(String srcNode, String tgtNode) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
						"var template = $.getWidget(canvas, 'templateCanvas');\n" +
						"var nodeArray = template.getAllNodes();\n" +
						"var sourceAnchor = null;\n" +
						"var targetAnchor = null;\n" +
						"var arrayLength = nodeArray.length;\n" +
						"for (var i = 0; i < arrayLength; i ++) {\n" +
						"    if(nodeArray[i].object.name === '%s' && i!= 3) {\n" +  // sourceTransformationName
						"        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
						"    } else if (nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
						"        targetAnchor = nodeArray[i].getInputAnchors()[0];\n" +
						"    }\n" +
						"}\n" +
						//"template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);" +
						"template.graphController.addEdge(\"testLink\",  nodeArray[2].getOutputAnchors()[1], nodeArray[3].getInputAnchors()[0]);";
		sLinkNodeJS = String.format(sLinkNodeJS, srcNode, tgtNode);
		return sLinkNodeJS;
	}


	public String UnlinkNodes(String srcNode, String tgtNode) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
	            "var template = $.getWidget(canvas, 'templateCanvas');\n" +
	            "var nodeArray = template.getAllNodes();\n" +
	            "var sourceAnchor = "+srcNode+";\n" +
	            "var targetAnchor = "+tgtNode+";\n" +
	            "var arrayLength = nodeArray.length;\n" +
	            "for (var i = 0; i < arrayLength; i ++) {\n" +
	            "    if(nodeArray[i].object.name === '%s') {\n" +  // sourceTransformationName
	            "        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
	            "    } else if (nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
	            "        targetAnchor = nodeArray[i].getInputAnchors()[0];\n" +
	            "    }\n" +
	            "}\n" +
	            "var noOfEdge = targetAnchor.edges.length;\n"+
				"for(var j=0;j<noOfEdge;j++){\n"+
				"	if(targetAnchor.edges[j].object.$fromTransformation.name==sourceAnchor.object.object.name){\n"+
				"		template.graphController.removeEdge(targetAnchor.edges[j])\n"+
				"	}\n"+
				"}\n";
		sLinkNodeJS = String.format(sLinkNodeJS, srcNode, tgtNode);
		return sLinkNodeJS;
	}

	public String LinkNodes(String srcNode, String tgtNode, boolean bMaster) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
	            "var template = $.getWidget(canvas, 'templateCanvas');\n" +
	            "var nodeArray = template.getAllNodes();\n" +
	            "var sourceAnchor = null;\n" +
	            "var targetAnchor = null;\n" +
	            "var arrayLength = nodeArray.length;\n" +
	            "for (var i = 0; i < arrayLength; i ++) {\n" +
	            "    if(nodeArray[i].object.name === '%s') {\n" +  // sourceTransformationName
	            "        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
	            "    } else if (nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
	            "        targetAnchor = nodeArray[i].getInputAnchors()[%d];\n" +
	            "    }\n" +
	            "}\n" +
	            "template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);";
		sLinkNodeJS = String.format(
				sLinkNodeJS,
				srcNode,
				tgtNode,
				(bMaster ? 0 : 1));
		return sLinkNodeJS;
	}

	public String LinkNodesWS(String srcNode, String tgtNode, boolean bSuccess) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
	            "var template = $.getWidget(canvas, 'templateCanvas');\n" +
	            "var nodeArray = template.getAllNodes();\n" +
	            "var sourceAnchor = null;\n" +
	            "var targetAnchor = null;\n" +
	            "var arrayLength = nodeArray.length;\n" +
	            "for (var i = 0; i < arrayLength; i ++) {\n" +
	            "    if(nodeArray[i].object.name === '%s') {\n" +  // sourceTransformationName
	            "        sourceAnchor = nodeArray[i].getOutputAnchors()[%d];\n" +
	            "    } else if (nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
	            "        targetAnchor = nodeArray[i].getInputAnchors()[0];\n" +
	            "    }\n" +
	            "}\n" +
	            "template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);";
		sLinkNodeJS = String.format(
				sLinkNodeJS,
				srcNode,
				(bSuccess ? 0 : 1),
				tgtNode);
		return sLinkNodeJS;
	}

	public String LinkMappletInputNodes(String srcNode,String tgtNode, String inputGrp){
		String sLinkNodeJS = "console.log(\"in the mapplet function\"); "+
		"var canvas = document.getElementById('designerIFrame').contentWindow.$('.infa-template-canvas-container-div');" +
		"var template = document.getElementById('designerIFrame').contentWindow.$.getWidget(canvas, 'templateCanvas');" +
		"var node_array = template.getAllNodes();" +
		"var arrlen = node_array.length;"+
		"var count=0;"+
		"var SrcIndex=0;"+
		"var TgtIndex = 0;"+
		"if(arrlen>0) {"+
			"for(count=0;count<arrlen;count++) {"+
				"if(node_array[count].object.name == '"+srcNode+"') {"+
					"SrcIndex=count; }"+
				"if(node_array[count].object.name == '"+tgtNode+"') {"+
					"TgtIndex=count; }"+
				"}"+
			"}"+
		"var inGrps = node_array[TgtIndex].object.inputGroups.length;"+
		"var inGrpIndex = 0;"+
		"if(inGrps>0) {"+
			"for(count=0;count<inGrps;count++) {"+
				"if(node_array[TgtIndex].object.inputGroups[count].name == '"+inputGrp+"') {"+
					"inGrpIndex=count; }"+
				"}"+
			"}"+
		"template.graphController.addEdge(\"testLink\", node_array[SrcIndex].getOutputAnchors()[0], node_array[TgtIndex].getInputAnchors()[inGrpIndex]);";
		return sLinkNodeJS;
	}

	public String LinkMappletOutputNodes(String srcNode,String tgtNode, String outputGrp){
		String sLinkNodeJS = "console.log(\"in the mapplet function\"); "+
		"var canvas = document.getElementById('designerIFrame').contentWindow.$('.infa-template-canvas-container-div');" +
		"var template = document.getElementById('designerIFrame').contentWindow.$.getWidget(canvas, 'templateCanvas');" +
		"var node_array = template.getAllNodes();" +
		"var arrlen = node_array.length;"+
		"var count=0;"+
		"var SrcIndex=0;"+
		"var TgtIndex = 0;"+
		"if(arrlen>0) {"+
			"for(count=0;count<arrlen;count++) {"+
				"if(node_array[count].object.name == '"+srcNode+"') {"+
					"SrcIndex=count; }"+
				"if(node_array[count].object.name == '"+tgtNode+"') {"+
					"TgtIndex=count; }"+
				"}"+
			"}"+
		"var outGrps = node_array[SrcIndex].getOutputAnchors().length;"+
		"var outGrpIndex = 0;"+
		"if(outGrps>0) {"+
			"for(count=0;count<outGrps;count++) {"+
				"if(node_array[SrcIndex].getOutputAnchors()[count].object.group.name == '"+outputGrp+"') {"+
					"outGrpIndex=count; }"+
				"}"+
			"}"+
		"template.graphController.addEdge(\"testLink\", node_array[SrcIndex].getOutputAnchors()[outGrpIndex], node_array[TgtIndex].getInputAnchors()[0]);";
		return sLinkNodeJS;
	}

/**
 * To link source and target node with output group (This will work for all the connectors other than Ariba, 
 * since the grpIndex for Ariba will start from 1 and in this method it will start from 0.
 * If you are getting error like (javascript error: Cannot read property 'object' of undefined) you can use LinkNodesHierarchyAriba() for the same
 * @param srcNode
 * @param tgtNode
 * @param outputGrp
 * @return
 */
	
	public String LinkNodesHierarchy(String srcNode, String tgtNode, String outputGrp){
		String sLinkNodeJS = "console.log(\"in the hierarchy function\"); "+
				"var canvas = $('.canvasContainer');" +
	            "var template = $.getWidget(canvas, 'templateCanvas');" +
				"var node_array = template.getAllNodes();" +
				"var count=0;"+
				"var SrcIndex=0;"+
				"var TgtIndex = 0;"+
				"var grpIndex=0;"+
				"var arrlen = node_array.length;"+
				"console.log(\"count is \"+arrlen);"+
				"if(arrlen>0) {"+
					"for(count=0;count<arrlen;count++) {"+
						"if(node_array[count].object.name == '"+srcNode+"') {"+
							"SrcIndex=count; }"+
						"else if(node_array[count].object.name == '"+tgtNode+"') {"+
							"TgtIndex=count; }"+
						"}"+
					"}"+
				"var anchor = template.getAllNodes()[SrcIndex].getOutputAnchors()[0];" +
				"var undoChange = template.graphController._createUndoChange(\"test\");"+
				"var grplen = node_array[SrcIndex].object.groups.length;"+
				"if(grplen>0) {"+
					"for(count=0;count<grplen;count++) {"+
						"if(node_array[SrcIndex].object.groups[count].name == '"+outputGrp+"') {"+
							"grpIndex=count; }"+
						"}"+
				"}"+
				"console.log(\"source index is \"+SrcIndex);"+
				"console.log(\"target index is \"+TgtIndex);"+
				"console.log(\"Output Group index is \"+grpIndex);"+
				"var imfObject = template.getGraphModel().addLink(\"test link\", anchor.object.outputs[grpIndex-1], template.getAllNodes()[TgtIndex].getInputAnchors()[0].getObject(), undoChange);	"+
				"template.addEdge(imfObject, anchor, template.getAllNodes()[TgtIndex].getInputAnchors()[0], true);" +
				"undoChange.end();";
				return sLinkNodeJS;
	}

	
	/**
	 * To link source and target node with output group (This will work for Ariba connector, 
	 * since the grpIndex for Ariba will start from 1 and for other connectors it will start from 0.
	 * @param srcNode
	 * @param tgtNode
	 * @param outputGrp
	 * @return
	 */
	
	public String LinkNodesHierarchyAriba(String srcNode, String tgtNode, String outputGrp){
		String sLinkNodeJS = "console.log(\"in the hierarchy function\"); "+
				"var canvas = $('.canvasContainer');" +
	            "var template = $.getWidget(canvas, 'templateCanvas');" +
				"var node_array = template.getAllNodes();" +
				"var count=0;"+
				"var SrcIndex=0;"+
				"var TgtIndex = 0;"+
				"var grpIndex=0;"+
				"var arrlen = node_array.length;"+
				"console.log(\"count is \"+arrlen);"+
				"if(arrlen>0) {"+
					"for(count=0;count<arrlen;count++) {"+
						"if(node_array[count].object.name == '"+srcNode+"') {"+
							"SrcIndex=count; }"+
						"else if(node_array[count].object.name == '"+tgtNode+"') {"+
							"TgtIndex=count; }"+
						"}"+
					"}"+
				"var anchor = template.getAllNodes()[SrcIndex].getOutputAnchors()[0];" +
				"var undoChange = template.graphController._createUndoChange(\"test\");"+
				"var grplen = node_array[SrcIndex].object.groups.length;"+
				"if(grplen>0) {"+
					"for(count=0;count<grplen;count++) {"+
						"if(node_array[SrcIndex].object.groups[count].name == '"+outputGrp+"') {"+
							"grpIndex=count+1; }"+
						"}"+
				"}"+
				"console.log(\"source index is \"+SrcIndex);"+
				"console.log(\"target index is \"+TgtIndex);"+
				"console.log(\"Output Group index is \"+grpIndex);"+
				"var imfObject = template.getGraphModel().addLink(\"test link\", anchor.object.outputs[grpIndex-1], template.getAllNodes()[TgtIndex].getInputAnchors()[0].getObject(), undoChange);	"+
				"template.addEdge(imfObject, anchor, template.getAllNodes()[TgtIndex].getInputAnchors()[0], true);" +
				"undoChange.end();";
				return sLinkNodeJS;
	}

	public String LinkNodesWSHierarchy(String srcNode, String tgtNode, String outputGrp){
		String sLinkNodeJS = "console.log(\"in the hierarchy function\"); "+
				"var canvas = document.getElementById('designerIFrame').contentWindow.$('.infa-template-canvas-container-div');" +
				"var template = document.getElementById('designerIFrame').contentWindow.$.getWidget(canvas, 'templateCanvas');" +
				"var node_array = template.getAllNodes();" +
				"var count=0;"+
				"var SrcIndex=0;"+
				"var TgtIndex = 0;"+
				"var grpIndex=0;"+
				"var arrlen = node_array.length;"+
				"console.log(\"count is \"+arrlen);"+
				"if(arrlen>0) {"+
					"for(count=0;count<arrlen;count++) {"+
						"if(node_array[count].object.name == '"+srcNode+"') {"+
							"SrcIndex=count; }"+
						"else if(node_array[count].object.name == '"+tgtNode+"') {"+
							"TgtIndex=count; }"+
						"}"+
					"}"+
				"var anchor = template.getAllNodes()[SrcIndex].getOutputAnchors()[0];" +
				"var undoChange = template.graphController._createUndoChange(\"test\");"+
				"var grplen = node_array[SrcIndex].object.groups.length;"+
				"if(grplen>0) {"+
					"for(count=0;count<grplen;count++) {"+
						"if(node_array[SrcIndex].object.groups[count].name == '"+outputGrp+"') {"+
							"grpIndex=count; }"+
						"}"+
				"}"+
				"console.log(\"source index is \"+SrcIndex);"+
				"console.log(\"target index is \"+TgtIndex);"+
				"console.log(\"Output Group index is \"+grpIndex);"+
				/* grpIndex-1 is used to retrieve the correct output group because of the presence of "DefaultGroup" as the 0th outputGroup*/
				"var imfObject = template.getGraphModel().addLink(\"test link\", anchor.object.outputs[grpIndex-1], template.getAllNodes()[TgtIndex].getInputAnchors()[0].getObject(), undoChange);	"+
				"template.addEdge(imfObject, anchor, template.getAllNodes()[TgtIndex].getInputAnchors()[0], true);" +
				"undoChange.end();";
				return sLinkNodeJS;
	}


	// TODO: There is "Arrange All" button. Not updated yet.
	public String ArrangeAll() {
		String sLinkNodeJS = "console.log(\"in the function\"); "+
				"var canvas = document.getElementById('designerIFrame').contentWindow.$('.infa-template-canvas-container-div');" +
				"var template = document.getElementById('designerIFrame').contentWindow.$.getWidget(canvas, 'templateCanvas');" +
				"template.arrangeAll()";
		return sLinkNodeJS;
	}

	// TODO: ?
	public String clickOnCheckboxWithSpecificText(String ckboxValue){
		String sValue = "var we; var count=0; "+
				"var canvas = document.getElementById('designerIFrame'); "+
				"var ck = canvas.contentWindow.$(\":checkbox\"); "+
				"var arrlen = ck.length ;"+
				"if(arrlen>0) {"+
					"for(count=0;count<arrlen;count++) {"+
						"if(ck[count].nextSibling.nodeValue.trim().toUpperCase()=='"+ckboxValue.toUpperCase()+"') {"+
							"we=ck[count]; "+
						 "} "+
					"}"+
				"}"+
				"we.click();";
		return sValue;
	}

	/**
	 * When executed, the script returns boolean result if transformation is present on canvas
	 */
	public String checkIfTransformationPresentOnMappingCanvas(String fieldName){
	    String script =
	    		"\n" +
						"function check(){\n" +
						"    var canvas = $('.canvasContainer');\n" +
						"    var template = $.getWidget(canvas, 'templateCanvas');\n" +
						"    var nodeArray = template.getAllNodes();\n" +
						"    var arrayLength = nodeArray.length;\n" +
						"    var booleanResult = false;\n" +
						"    for (var i = 0; i < arrayLength; i ++) {\n" +
						"        if(nodeArray[i].object.name === '%s') {\n" +
						"        booleanResult = true;\n" +
						"        break;\n" +
						"        }\n" +
						"    }\n" +
						"    return booleanResult;\n" +
						"}\n" +
						"return check()\n" +
						"\n";
	    return String.format(script, fieldName);
	}

	/**
	 * When executed, the script returns a string result of all edges in the format as below
	 * output ->  "sourcename>targetname,sourcename>targetname,sourcename>targetname"
	 */
	public String getAllEdgesOnMappingCanvas(){
		String script =
					"function getAllEdgesJS(){\n" +
							"var canvas = $('.canvasContainer');\n" +
							"var template = $.getWidget(canvas, 'templateCanvas');\n" +
							"var edgeArray = template.getAllEdges();\n" +
							"var result=\"\";\n" +
							"for(var i=0; i<edgeArray.length; i++){\n" +
							" result += edgeArray[i].object.$fromTransformation.name+\">\"+edgeArray[i].object.$toTransformation.name+\",\";\n" +
							" }\n" +
							" result = result.slice(0,-1);\n" +
							" return result;\n" +
							"}\n" +
							"return getAllEdgesJS()";
		return script;
	}


	/**
	 * When executed, the script returns a string of all transformations seperated by comma
	 */
	public String getAllLicensedTransformationsOnMappingCanvas(){
		String script=
				"function getAllTransformations(){\n" +
						"var canvas = $('.canvasContainer');\n" +
						"var template = $.getWidget(canvas, 'templateCanvas');\n" +
						"var transformationsArray = template.transformations;\n" +
						"var result=\"\";\n" +
						"for(var i=0; i<transformationsArray.length; i++){\n" +
						" result += transformationsArray[i].logicalName+\",\";\n" +
						" }\n" +
						" result = result.slice(0,-1);\n" +
						" return result;\n" +
						"}\n" +
						"return getAllTransformations()";
		return script;
	}
	/**
	 * @param sourceTxName Tx name of source
	 * @param groupName groupName Anchor of Joiner Tx
	 * @param joinerTxName Joiner Tx Name
	 * @return String, javascript code to connect source with Anchor of Joiner by their name
	 */
	public String LinkWithJoinerGroups(String sourceTxName, String joinerTxName, String groupName) {
		String sLinkNodeJS =
				"var canvas = $('.canvasContainer');\n" +
						"var template = $.getWidget(canvas, 'templateCanvas');\n" +
						"var nodeArray = template.getAllNodes();\n" +
						"var sourceAnchor = null;\n" +
						"var targetAnchor = null;\n" +
						"var arrayLength = nodeArray.length;\n" +
						"for (var i = 0; i < arrayLength; i ++) {\n" +
						"    if(nodeArray[i].object.name === '%s') {\n" +  // sourceTransformationName //&& i!= 3
						"        sourceAnchor = nodeArray[i].getOutputAnchors()[0];\n" +
						"    } else if(nodeArray[i].object.name === '%s') {\n" +  // targetTransformationName
						"		var anchorArray = nodeArray[i]._inputAnchors;\n" +
						"		var inputAnchorLength = anchorArray.length;\n" +
						"		for(var j=0; j < inputAnchorLength;j++){\n"+
						" 			if(anchorArray[j].object.group.name=== '%s') {\n"+
						"				 targetAnchor = nodeArray[i].getInputAnchors()[j];\n" +
						"    			}\n" +
						"   		 }\n" +
						"   	}\n" +
						"}\n" +
						"template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);";
		sLinkNodeJS = String.format(sLinkNodeJS, sourceTxName, joinerTxName,groupName);
		return sLinkNodeJS;
	}

	/**
	 * @param routerTxName Tx name of the router Tx
	 * @param groupName    groupName Anchor of Router Tx
	 * @param targetTxName Name of the Transformation to connect with Router
	 * @return This Method return javascript for connecting Router Groups to any Transformation
	 */
	public String LinkRouterGroupWithTarget(String routerTxName, String groupName, String targetTxName){
		String sLinkNodeJS=
				"var canvas = $('.canvasContainer');\n"+
						"var template = $.getWidget(canvas, 'templateCanvas');\n"+
						"var nodeArray = template.getAllNodes();\n"+
						"var sourceAnchor = null;\n"+
						"var targetAnchor = null;\n"+
						"var arrayLength = nodeArray.length;\n"+
						"for (var i = 0; i < arrayLength; i ++) {\n"+
						"    if(nodeArray[i].object.name === '%s') {\n"+  // sourceTransformationName
						"		var anchorArray = nodeArray[i]._outputAnchors;\n"+
						"		var outputAnchorLength = anchorArray.length;\n"+
						"		for(var j=0; j < outputAnchorLength;j++){\n"+
						" 			if(anchorArray[j].object.group.name=== '%s') {\n"+
						"				 sourceAnchor = nodeArray[i].getOutputAnchors()[j];\n"+
						"    			}\n"+
						"   		 }\n"+
						"    } else if (nodeArray[i].object.name === '%s') {\n"+  // targetTransformationName
						"        targetAnchor = nodeArray[i].getInputAnchors()[0];\n"+
						"    }\n"+
						"}\n"+
						"template.graphController.addEdge(\"testLink\", sourceAnchor, targetAnchor);";
		//"template.graphController.addEdge(\"testLink\",  nodeArray[1].getOutputAnchors()[0], nodeArray[2].getInputAnchors()[1]);";
		sLinkNodeJS=String.format(sLinkNodeJS, routerTxName, groupName, targetTxName);
		return sLinkNodeJS;
	}


}


