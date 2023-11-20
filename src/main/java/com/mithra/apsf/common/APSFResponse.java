package com.mithra.apsf.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.mithra.apsf.common.Constants.EnumResponseStatus;
import com.mithra.apsf.exception.APSFException;

/**
 * 
 */
public final class APSFResponse {
    private EnumResponseStatus responseStatus=EnumResponseStatus.OK;

    private StatusCode statusCode;

    private String message;

    private Map<String, Object> data = null;

    private Object fullData = null;
    
    private String referUrl;
    
    private String redirectUrl;;
    
    private String requestUrl;
    
    /**
	 * @return the referUrl
	 */
	public String getReferUrl() {
		return referUrl;
	}

	/**
	 * @param referUrl the referUrl to set
	 */
	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}

	/**
	 * @return the redirectUrl
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * @param redirectUrl the redirectUrl to set
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * @param requestUrl the requestUrl to set
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public APSFResponse()
    {
        setResponseStatus( EnumResponseStatus.OK );
        data = new HashMap<>( 2 );
    }

    /**
     * @return the responseStatus
     */
    public EnumResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    /**
     * @param responseStatus
     *            the responseStatus to set
     */
    public void setResponseStatus( EnumResponseStatus responseStatus )
    {
        this.responseStatus = responseStatus;
    }

    /**
     * @return
     */
    public StatusCode getStatusCode()
    {
        return statusCode;
    }

    /**
     * @param statusCode
     */
    public APSFResponse setStatusCode( final StatusCode statusCode )
    {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * @return
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message
     */
    public APSFResponse setMessage( final String message )
    {
        this.message = message;
        return this;
    }

    /**
     * @param entity
     * @return
     */
    public APSFResponse add( final Object entity )
    {
        if ( entity == null )
        {
            return this;
        }
        return add( getKey( entity ), entity );
    }

    /**
     * @param key
     * @param entity
     * @return
     */
    public APSFResponse add( final String key, final Object entity )
    {
        if ( entity == null )
        {
            return this;
        }
        String trKey = Purifier.clean( key );
        if ( StringUtils.hasLength( trKey ) )
        {
            data.put( trKey, entity );
        }
        return this;
    }

    public APSFResponse add( final StatusCode key, final Object entity )throws Exception
    {
    	try{
    		
    		if ( entity == null || key ==null)
            {
                return this;
            }
            
            data.put( key.name(), entity );
            
    	}catch(Exception e){
    		throw new Exception("Exception at adding status code to Data Object"+e.getMessage());
    	}
        
        return this;
    }
    /**
     * @param other
     * @return
     */
    public APSFResponse add( final APSFResponse other )
    {
        if ( other != null )
        {
            // Let us copy the statusCode and message only if the statusCode is
            // not OK
            if ( other.getStatusCode() != StatusCode.OK )
            {
                setStatusCode( other.getStatusCode() );
                setMessage( other.getMessage() );
            }
            Map<String, Object> otherData = other.getData();
            if ( otherData != null && !otherData.isEmpty() )
            {
                for ( Map.Entry<String, Object> entry : otherData.entrySet() )
                {
                    if ( StringUtils.hasLength( entry.getKey() ) && entry.getValue() != null )
                    {
                        if ( data.containsKey( entry.getKey() ) )
                        {
                            System.out.println( ">>> Map already contains " + entry.getKey()
                                    + " and is replaced with a new value <<<" );
                        }
                        data.put( entry.getKey(), entry.getValue() );
                    }
                }
            }
        }
        return this;
    }

    /**
     * @return
     */
    public Map<String, Object> getData()
    {
        if ( data == null )
        {
            data = new HashMap<>( 0 );
        }
        return data;
    }

    /**
     * @param key
     * @return
     */
    public Object get( String key )
    {
        Object value = null;
        String trKey = Purifier.clean( key );
        if ( StringUtils.hasLength( trKey ) )
        {
            value = data.get( trKey );
        }
        return value;
    }

    /**
     * @param projectedTo
     * @param <T>
     * @return
     */
    public <T> T get( final Class<T> projectedTo )
    {
        if ( projectedTo != null )
        {
            return get( projectedTo.getSimpleName().toLowerCase(), projectedTo );
        }
        return null;
    }

    /**
     * @param key
     * @param projectedTo
     * @param <T>
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public <T> T get( final String key, final Class<T> projectedTo )
    {
        T projectedEntity = null;
        String trKey = Purifier.clean( key );
        if ( StringUtils.hasLength( trKey ) )
        {
            Object value = data.get( trKey );
            projectedEntity = projectedTo.isAssignableFrom( value.getClass() ) ? projectedTo.cast( value ) : (T) value;
        }
        return projectedEntity;
    }

    /**
     * @param obj
     * @return
     */
    private String getKey( final Object obj )
    {
        String key = null;
        if ( obj != null )
        {
            key = obj.getClass().getSimpleName().toLowerCase();
        }
        return key;
    }

    /**
     * @return the fullData
     */
    public Object getFullData()
    {
        return fullData;
    }

    /**
     * @param fullData
     *            the fullData to set
     */
    public void setFullData( Object fullData )
    {
        this.fullData = fullData;
    }
    
    /**
     * @param data
     *            the Data to set
     */
    public void setData( Map<String,Object> Data )
    {
        this.data.putAll(Data);
       
    }

    /**
     * Remove key from Data Map
     */
    public void remove(String key)throws APSFException
    {
    	if(data.containsKey(key))
    	{
    		data.remove(key);
    	}else{
    		throw new APSFException("The given key does not exist");
    	}
    	
    }
}