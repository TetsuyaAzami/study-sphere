import { ChangeEvent, useState } from "react";

export const useUsername = () => {
  //
  const [username, setUsername] = useState("");
  const [usernameInvalidLengthMessage, setUsernameInvalidLengthMessage] =
    useState("");

  const maxLength: number = 30;
  const minLength: number = 6;

  const handleUsernameChange = (e: ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handleUsernameBlur = (e: any) => {
    //
    const value = e.target.value;

    if (value.length < minLength || maxLength < value.length) {
      setUsernameInvalidLengthMessage(
        `ユーザ名は${minLength}文字以上${maxLength}文字以下で入力してください。`
      );
    } else {
      setUsernameInvalidLengthMessage("");
    }
  };

  return {
    username,
    usernameInvalidLengthMessage,
    maxLength,
    minLength,
    handleUsernameChange,
    handleUsernameBlur,
  };
};
