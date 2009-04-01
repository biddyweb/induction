package com.acciente.induction.resolver;

import com.acciente.commons.loader.ClassDefLoader;
import com.acciente.commons.loader.ReloadingClassLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Internal.
 *
 * URL2ClassMapper
*
* @author Adinath Raveendra Raj
* @created Mar 30, 2009
*/
class URL2ClassMapper
{
   private  Pattern  _oURLPattern;
   private  Map      _oShortName2ClassnameMap;

   URL2ClassMapper( Pattern oURLPattern, Pattern oClassPattern, ReloadingClassLoader oClassLoader )
   {
      _oURLPattern = oURLPattern;

      // build a mapping for all classes foun in the system that match the specified class pattern
      _oShortName2ClassnameMap = new HashMap();

      for ( Iterator oClassDefLoaderIter = oClassLoader.getClassDefLoaders().iterator(); oClassDefLoaderIter.hasNext(); )
      {
         ClassDefLoader oClassDefLoader = ( ClassDefLoader ) oClassDefLoaderIter.next();
         Collection     oClassnames     = oClassDefLoader.findClassNames( oClassPattern );

         for ( Iterator oClassnameIter = oClassnames.iterator(); oClassnameIter.hasNext(); )
         {
            String   sClassName = ( String ) oClassnameIter.next();
            Matcher oClassMatcher;

            oClassMatcher = oClassPattern.matcher( sClassName );

            if ( oClassMatcher.matches() )
            {
               String   sShortName = oClassMatcher.group( 1 );

               if ( sShortName != null )
               {
                  _oShortName2ClassnameMap.put( sShortName.toLowerCase(), sClassName );
               }
            }
         }
      }
   }

   ClassAndMethod mapURL2Class( String sURLPath )
   {
      Matcher oURLMatcher = _oURLPattern.matcher( sURLPath );

      if ( oURLMatcher.matches() )
      {
         String   sShortName;

         sShortName = oURLMatcher.group( 1 );

         if ( sShortName != null )
         {
            String   sClassName;

            sClassName = ( String ) this._oShortName2ClassnameMap.get( sShortName.toLowerCase() );

            if ( sClassName != null )
            {
               String   sMethodname = null;

               if ( oURLMatcher.groupCount() > 1 )
               {
                  sMethodname = oURLMatcher.group( 2 );
               }

               return new ClassAndMethod( sClassName, sMethodname );
            }
         }
      }

      return null;
   }

   public static class ClassAndMethod
   {
      private  String   _sClassName;
      private  String   _sMethodName;

      public ClassAndMethod( String sClassName, String sMethodName )
      {
         _sClassName    = sClassName;
         _sMethodName   = sMethodName;
      }

      public String getClassName()
      {
         return _sClassName;
      }

      public String getMethodName()
      {
         return _sMethodName;
      }
   }
}
