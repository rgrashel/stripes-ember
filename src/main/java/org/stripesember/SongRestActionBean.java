/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stripesember;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import org.stripesrest.JsonResolution;
import org.stripesrest.RestActionBean;

/**
 *
 * @author Rick_Grashel
 */
@UrlBinding( "/songs" )
public class SongRestActionBean implements RestActionBean {
    
    @Validate( required = true, on = "post" )
    private Artist artist;
    
    @Validate( required = true, on = "post" )
    private String songTitle;
    
    public Resolution post()
    {
      Song song = new Song( null, songTitle, 0 );
      song = Datastore.createSong( artist, song );
      return new JsonResolution( song );
    }
    
    public void setArtist( Artist artist )
    {
        this.artist = artist;
    }
    
    public Artist getArtist()
    {
        return this.artist;
    }
    
    public void setSongTitle( String songTitle )
    {
        this.songTitle = songTitle;
    }
    
    public String getSongTitle()
    {
        return this.songTitle;
    }
    
    public ActionBeanContext getContext() { return this.context; }
    public void setContext( ActionBeanContext context ) { this.context = context; }
    private ActionBeanContext context;
}
