import React, {useMemo,useCallback,useEffect} from 'react'
import {useDropzone} from 'react-dropzone'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const baseStyle = {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: '20px',
    borderWidth: 2,
    borderRadius: 2,
    borderColor: '#eeeeee',
    borderStyle: 'dashed',
    backgroundColor: '#fafafa',
    color: '#bdbdbd',
    outline: 'none',
    transition: 'border .24s ease-in-out'
  };
  
  const activeStyle = {
    borderColor: '#2196f3'
  };
  
  const acceptStyle = {
    borderColor: '#00e676'
  };
  
  const rejectStyle = {
    borderColor: '#ff1744'
  };

const UploadFiles = (props) => {

    let {uploadStudentCSV} = props; 

    useEffect(() =>{

    },[]);

    const onDrop = useCallback(acceptedFiles => {
        // Do something with the files

        uploadStudentCSV(acceptedFiles[0]);
      }, [])
    const {
      getRootProps,
      getInputProps,
      isDragActive,
      isDragAccept,
      isDragReject,
      acceptedFiles,
      fileRejections
    } = useDropzone({accept: '.csv',onDrop});
    
      const acceptedFileItems = acceptedFiles.map(file => (
        <li key={file.path}>
          {file.path} - {file.size} bytes
        </li>
      ));
    
      const fileRejectionItems = fileRejections.map(({ file, errors }) => (
        <li key={file.path}>
          {file.path} - {file.size} bytes
          <ul>
            {errors.map(e => (
              <li key={e.code}>{e.message}</li>
            ))}
          </ul>
        </li>
      ));
  
    const style = useMemo(() => ({
      ...baseStyle,
      ...(isDragActive ? activeStyle : {}),
      ...(isDragAccept ? acceptStyle : {}),
      ...(isDragReject ? rejectStyle : {})
    }), [
      isDragActive,
      isDragReject,
      isDragAccept
    ]);
  
    return (
      <><Row>
        <Col sm={12}><br></br>
          <div className="container" style={{display: 'flex', justifyContent: 'center'}}>
            <div {...getRootProps({style})}>
              <input {...getInputProps()} />
              <p>Drag 'n' drop some files here, or click to select files</p>
            </div>
            
          </div>
        </Col>
      </Row>
      <Row>
      <Col sm={12}><br></br>
          <div className="container" style={{display: 'flex', justifyContent: 'center'}}>
            
            <aside style={{display: 'flex', justifyContent: 'center'}}><br></br><br></br>
            <h4><br></br>Accepted files</h4>
            <ul>{acceptedFileItems}</ul>
            <h4><br></br>Rejected files</h4>
            <ul>{fileRejectionItems}</ul>
          </aside>
          </div>
        </Col>
      </Row>
        
      </>
    );
  }
export default UploadFiles;