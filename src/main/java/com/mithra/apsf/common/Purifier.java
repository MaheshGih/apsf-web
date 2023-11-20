package com.mithra.apsf.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.StringUtils;


/**
 * 
 * Author: Suneel Ayyaparaju.
 */
public final class Purifier
{
	/**
	 * Reference to the logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(Purifier.class);
	
	/**
     * @param input
     * @return
     */
    public static String clean( String input )
    {
        return StringUtils.trimWhitespace( input );
    }

    /**
     * @param input
     * @return
     */
    public static String clean( String input, String defaultValueIfNullOrEmpty )
    {
    	logger.debug("Entering clean");
        String trInput = Purifier.clean( input );
        logger.debug("Leaving");
        return StringUtils.hasLength( trInput ) ? trInput : defaultValueIfNullOrEmpty;
    }

    /**
     * @param inputs
     * @return
     */
    public static List<String> cleanArray( List<String> inputs )
    {
    	logger.debug("Entering cleanArray");
        List<String> cleansedList = null;

        if ( inputs != null && !inputs.isEmpty() )
        {
            cleansedList = new ArrayList<String>( inputs.size() );
            for ( String input : inputs )
            {
                String trInput = Purifier.clean( input );
                if ( StringUtils.hasLength( trInput ) )
                {
                    cleansedList.add( trInput );
                }
            }
            cleansedList = cleansedList.size() > 0 ? cleansedList : null;
        }
        
        logger.debug("Leaving"); 
        return cleansedList;
    }

    /**
     * @param url
     * @return
     */
    
    public static String cleanUrl( String url )
    {
    	logger.debug("Entering cleanUrl");
        String trUrl = Purifier.clean( url );
        // Is this a valid url i.e. with the scheme?
        if ( StringUtils.hasLength( trUrl ) && !UrlUtils.isAbsoluteUrl( trUrl ) )
        {
            trUrl = Constants.PREFIX_HTTP + trUrl;
        }
        
        logger.debug("Leaving"); 
        return trUrl;
    }

   
    
    /**
     * Delete given file is either a directory or file  
     * @param file
     */
    public static void deleteFile(File file)
    {
    	try
		{
    		if(file == null)
    		{
    			return ;
    		}
    		
    		//if file not exist omit deleting operation
    		if(!file.exists())
    		{
    			return;
    		}
    		//checking given file is a directory or file
    		if(file.isDirectory())
    		{
    			//delete staging directory
    			String files[] = file.list();
    			for (String name : files) {
    				//construct the file structure
    				
    				File fileDelete = new File(file, name);
    				if(fileDelete.isDirectory())
    				{
    					deleteFile(fileDelete);
    				}
    				//recursive delete
    				fileDelete.delete();
    			}
    		}
    		else
    		{
    			file.delete();
    		}
 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

    /**
     * Delete all files of specified path
     * @param filePath
     */
    public static void deleteFile(String filePath)
    {
    	File file = new File(filePath);
    	deleteFile(file);
    }
}