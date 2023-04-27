import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import CategoryInput from "../../components/inputs/CategoryInput";
import { categories } from "../../components/navbar/Categories";
import Heading from "../../components/Heading";
import { useForm } from "react-hook-form";
import CountrySelect from "../../components/inputs/CountrySelect";
import { useMemo } from "react";
import { useCallback } from "react";
import Input from "../../components/inputs/Input";
import { useEffect } from "react";
import Map from "../../components/Map";
import Counter from "../../components/inputs/Counter";
import ImageUpload from "../../components/inputs/ImageUpload";
import Button from "../../components/Button";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import newRequest from "../../utils/newRequest";
import { useSelector } from "react-redux";
import axios from "axios";
import { toast } from "react-hot-toast";

const ListingForm = () => {
  const token = useSelector((state) => state?.token);
  const user = useSelector((state) => state?.user);
  const [image, setImage] = useState(null);
  const navigate = useNavigate();
  const queryClient = useQueryClient();
  const [imagePreview, setImagePreview] = useState(null);

  const {
    register,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      category: "",
      location: null,
      guestCount: 1,
      roomCount: 1,
      bathroomCount: 1,
      imageSrc: null,
      price: 1,
      title: "",
      description: "",
    },
  });

  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
  }, []);

  const mutation = useMutation({
    mutationFn: (listing) => {
      return newRequest.post("/listing", listing, {
        headers: { Authorization: `Bearer ${token}` },
      });
    },
    onSuccess: () => {
      queryClient.invalidateQueries(["listings"]);
      toast.success("Listing save sucess");
      navigate("/");
    },
  });

  const saveImage = async () => {
    const formData = new FormData();
    formData.append("file", image);

    const savedUserImageResponse = await axios.post(
      "http://localhost:8081/image/upload",
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    return savedUserImageResponse;
  };

  const listingSubmit = async (data) => {
    const result = await saveImage();

    mutation.mutate({
      ...data,
      location: location.label,
      email: user.email,
      imgPath: result.data.link,
      uuid: result.data.uuid,
      latlng: JSON.stringify(location.latlng),
    });
  };

  const category = watch("category");
  const location = watch("location");
  const guestCount = watch("guestCount");
  const roomCount = watch("roomCount");
  const bathroomCount = watch("bathroomCount");
  const imageSrc = watch("imageSrc");

  const setCustomValue = (id, value) => {
    setValue(id, value, {
      shouldDirty: true,
      shouldTouch: true,
      shouldValidate: true,
    });
  };

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    setImage(event.target.files[0]);
    if (!file) {
      setImagePreview(null);
      return;
    }
    const reader = new FileReader();
    reader.onloadend = () => {
      setImagePreview(reader.result);
      onChange(file);
    };
    reader.readAsDataURL(file);
  };

  return (
    <div className="flex items-center justify-center">
      <div className="relative w-full mx-auto my-6 lg:w-4/6 xl:w-3/5 md:h-auto">
        {/* category */}

        <Heading title="카테고리" subtitle="Pick a category" />
        <div
          className="
        grid 
        grid-cols-2
        sm:grid-cols-3
        md:grid-cols-5 
        gap-3
        max-h-[30vh]
        lg:grid-cols-5
        overflow-y-auto
        overflow-x-auto
   
        
      "
        >
          {categories.map((item) => (
            <div key={item.label} className="col-span-1">
              <CategoryInput
                onClick={(category) => setCustomValue("category", category)}
                selected={category == item.label}
                label={item.label}
                Icon={item.icon}
              />
            </div>
          ))}
        </div>

        {/* Map */}

        <div className="flex flex-col gap-8 mt-4">
          <Heading title="위치" subtitle="Help guests find you!" />
          <CountrySelect
            value={location}
            onChange={(value) => setCustomValue("location", value)}
          />

          <Map center={location?.latlng} />
        </div>
        {/* 기본옵션 */}
        <hr />
        <div className="flex flex-col gap-8 mt-6">
          <Heading title="기본 옵션" subtitle="What amenitis do you have?" />
          <Counter
            onChange={(value) => setCustomValue("guestCount", value)}
            value={guestCount}
            title="총인원"
            subtitle="How many guests do you allow?"
          />
          <hr />
          <Counter
            onChange={(value) => setCustomValue("roomCount", value)}
            value={roomCount}
            title="방 개수"
            subtitle="How many rooms do you have?"
          />
          <hr />
          <Counter
            onChange={(value) => setCustomValue("bathroomCount", value)}
            value={bathroomCount}
            title="욕실"
            subtitle="How many bathrooms do you have?"
          />
        </div>
        {/* 사진 */}
        <div className="flex flex-col gap-8 mt-6">
          <hr />
          <Heading
            title="사진"
            subtitle="Show guests what your place looks like!"
          />
          {imagePreview && (
            <div className="w-full h-full ">
              <img
                src={imagePreview}
                alt="Selected"
                className="preview-image"
              />
            </div>
          )}
          <label htmlFor="image-input">Select an image:</label>
          <input
            type="file"
            id="image-input"
            accept="image/*"
            onChange={handleImageChange}
          />

          {/* <ImageUpload onChange={(value) => setImage(value)} value={imageSrc} /> */}
        </div>
        {/* 타이틀 */}
        <div className="flex flex-col gap-8 mt-7">
          <Heading title="설명" subtitle="Short and sweet works best!" />
          <Input
            id="title"
            label="Title"
            register={register}
            errors={errors}
            required
          />
          <hr />
          <Input
            id="description"
            label="Description"
            register={register}
            errors={errors}
            required
          />
        </div>
        {/* 가격 */}
        <div className="flex flex-col gap-8 mt-4">
          <Heading title="가격" subtitle="How much do you charge per night?" />
          <Input
            id="price"
            label="Price"
            formatPrice
            type="number"
            register={register}
            errors={errors}
            required
          />
        </div>

        <div className="flex flex-col gap-2 p-6">
          <div className="flex flex-row items-center w-full gap-4 ">
            <Button
              label={"저장"}
              onClick={handleSubmit(listingSubmit)}
              outline
            />

            <Button label={"취소"} onClick={handleSubmit} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ListingForm;
