import { useDropzone } from "react-dropzone";
import { useCallback } from "react";
import { TbPhotoPlus } from "react-icons/tb";

const ImageUpload = ({ onChange, value }) => {
  const onDrop = useCallback(
    (acceptedFiles) => {
      console.log(acceptedFiles);
    },
    [onChange]
  );

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div className="relative flex flex-col items-center justify-center gap-4 p-20 transition border-2 border-dashed cursor-pointer hover:opacity-70 border-neutral-300 text-neutral-600">
      <div className="text-lg font-semibold">Click to upload</div>

      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {isDragActive ? <TbPhotoPlus size={50} /> : <TbPhotoPlus size={50} />}
      </div>
    </div>
  );
};

export default ImageUpload;
