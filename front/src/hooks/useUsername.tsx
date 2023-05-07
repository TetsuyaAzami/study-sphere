import { ChangeEvent, FocusEvent, useState } from "react";

export const useUsername = () => {
  //
  const [username, setUsername] = useState<string>("");
  const [usernameInvalidLengthMessage, setUsernameInvalidLengthMessage] =
    useState<string>("");

  const maxLength: number = 30;
  const minLength: number = 6;

  const isUsernameValid = (username: string): boolean => {
    return minLength <= username.length && username.length <= maxLength;
  };

  const handleUsernameChange = (e: ChangeEvent<HTMLInputElement>): void => {
    setUsername(e.target.value);
  };

  const handleUsernameBlur = (e: FocusEvent<HTMLInputElement>): void => {
    //
    const value = e.target.value;

    if (!isUsernameValid(value)) {
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
    handleUsernameChange,
    handleUsernameBlur,
  };
};
