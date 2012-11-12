package org.jboss.demos.client;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author <a href="mailto:yyang@redhat.com">Yong Yang</a>
 * @create 11/12/12 10:32 AM
 */
public class Cluster {

    final double width;
    final double height;
    final int numLogos;
    final double radius;

    Image nodeImg;
    Node[] nodes;
    boolean imageLoaded;

    double k;

    public Cluster(double width, double height, int numNodes, double radius) {
        this.width = width;
        this.height = height;
        this.numLogos = numNodes;
        this.radius = radius;

        // init logos array
        nodes = new Node[numNodes];

        // init image
        nodeImg = new Image("raspeberry-pi-logo.jpg");
        nodeImg.addLoadHandler(new LoadHandler() {
            public void onLoad(LoadEvent event) {
                imageLoaded = true;
                // once image is loaded, init logo objects
                ImageElement imageElement = (ImageElement) nodeImg.getElement().cast();
                for (int i = nodes.length - 1; i >= 0; i--) {
                    Node node = new Node(imageElement);
                    node.setPosition(Cluster.this.width / 2, Cluster.this.height / 2);
                    nodes[i] = node;
                }
            }
        });
        nodeImg.setVisible(false);
        RootPanel.get().add(nodeImg); // image must be on page to fire load
    }

    public void update(double mouseX, double mouseY) {
        if (!imageLoaded) {
            return;
        }

        k = (k + Math.PI/2.0 * 0.009);

        for (int i = numLogos - 1; i >= 0; i--) {
            Node node = nodes[i];
            double logoPerTPi = 2 * Math.PI * i / numLogos;
            Vector goal = new Vector(width / 2 + radius * Math.cos(k + logoPerTPi),
                    height / 2 + radius * Math.sin(k + logoPerTPi));
            node.setGoal(goal.x, goal.y);

            node.update();
        }
    }

    public void draw(Context2d context) {
        if (!imageLoaded) {
            return;
        }

/*
        context.beginPath();
        context.setFillStyle(CssColor.make("blue"));
        context.arc(100, 100, 100, 0, Math.PI);
        context.fill();
        context.closePath();
*/

        for (int i = numLogos - 1; i >= 0; i--) {
            nodes[i].draw(context);
        }
    }

}