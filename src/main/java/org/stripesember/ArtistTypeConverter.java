/*
 * Copyright 2014 Rick Grashel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.stripesember;

import java.util.Collection;
import java.util.Locale;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;
import org.stripesrest.ResourceNotFoundError;

/**
 * This is a type converter for Artist objects.
 */
public class ArtistTypeConverter implements TypeConverter< Artist>
{

  @Override
  public void setLocale( Locale locale )
  {
  }

  /**
   * Takes the passed identifier and tries to look up a corresponding 
   * Artist in the datastore and return it.
   * 
   * @param artistIdentifier
   * @param targetType
   * @param errors
   * @return 
   */
  @Override
  public Artist convert( String artistIdentifier,
          Class<? extends Artist> targetType,
          Collection<ValidationError> errors )
  {
    // If no identifier is passed, just return null.
    if ( artistIdentifier == null )
    {
      return null;
    }

    // First, try to retrieve the artist by name
    Artist artist = Datastore.getArtistByName( artistIdentifier );

    // If artist is null then try to look up by ID
    if ( artist == null )
    {
      try
      {
        artist = Datastore.getArtistById( Long.parseLong( artistIdentifier ) );
      }
      catch ( NumberFormatException nfe )
      {
        // We don't care about this.  It is the same result.
      }

      // If the artist is not found by the TypeConverter, then create a 
      // ResourceNotFoundError which will be passed back to the caller
      if ( artist == null )
      {
        errors.add( new ResourceNotFoundError( "Unable to find artist by identifier passed => " + artistIdentifier ) );
      }
    }

    return artist;
  }
}
