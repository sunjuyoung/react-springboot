import React, { useState, Suspense, lazy } from "react";
import { useParams } from "react-router-dom";
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
const ListingForm = () => {
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
      imageSrc: "",
      price: 1,
      title: "",
      description: "",
    },
  });

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

  return (
    <div className="flex items-center justify-center ">
      <div className="relative w-full mx-auto my-6 lg:w-4/6 xl:w-3/5 md:h-auto">
        <h1>hi</h1>
        {/* category */}

        <Heading
          title="Which of these best describes your place?"
          subtitle="Pick a category"
        />
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

        <div className="flex flex-col gap-8">
          <Heading
            title="Where is your place located?"
            subtitle="Help guests find you!"
          />
          <CountrySelect
            value={location}
            onChange={(value) => setCustomValue("location", value)}
          />

          <Map center={location?.latlng} />
        </div>

        <hr />
        <div className="flex flex-col gap-8 mt-6">
          <Heading title="기본 정보" subtitle="What amenitis do you have?" />
          <Counter
            onChange={(value) => setCustomValue("guestCount", value)}
            value={guestCount}
            title="Guests"
            subtitle="How many guests do you allow?"
          />
          <hr />
          <Counter
            onChange={(value) => setCustomValue("roomCount", value)}
            value={roomCount}
            title="Rooms"
            subtitle="How many rooms do you have?"
          />
          <hr />
          <Counter
            onChange={(value) => setCustomValue("bathroomCount", value)}
            value={bathroomCount}
            title="Bathrooms"
            subtitle="How many bathrooms do you have?"
          />
        </div>

        <div className="flex flex-col gap-8 mt-6">
          <hr />
          <Heading
            title="사진"
            subtitle="Show guests what your place looks like!"
          />
          <ImageUpload
            onChange={(value) => setCustomValue("imageSrc", value)}
            value={imageSrc}
          />
        </div>

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
            <Button label={"저장"} onClick={() => {}} outline />

            <Button label={"취소"} onClick={handleSubmit} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ListingForm;
